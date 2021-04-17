package cz.tul;

import cz.tul.data.Country;
import cz.tul.data.CountryDao;
import cz.tul.data.Town;
import cz.tul.data.TownDao;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {

    @Bean
    public TownDao townDao() {
        return new TownDao();
    }

    @Bean
    public CountryDao countryDao() {
        return new CountryDao();
    }

    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        Provisioner provisioner = ctx.getBean(Provisioner.class);

        CountryDao countryDao = ctx.getBean(CountryDao.class);
        countryDao.deleteCountries();
        TownDao townDao = ctx.getBean(TownDao.class);


        Country czechRepublic = new Country("Česká Republika");
        Country slovakia = new Country("Slovensko");

        countryDao.create(czechRepublic);
        countryDao.create(slovakia);

        Town prague = new Town("Praha",czechRepublic);
        Town brno = new Town("Brno",czechRepublic);
        Town liberec = new Town("Liberec",czechRepublic);

        townDao.create(prague);
        townDao.create(brno);
        townDao.create(liberec);


        Town bratislava = new Town("Bratislava",slovakia);
        Town kosice = new Town("Košice",slovakia);
        Town trencin = new Town("Trenčín",slovakia);

        townDao.create(bratislava);
        townDao.create(kosice);
        townDao.create(trencin);

        List<Country> countries = countryDao.getAllCountries();
        List<Town> towns = townDao.getTowns();
        System.out.println(countries);
        System.out.println(towns);

        townDao.delete(prague);
        townDao.delete(bratislava);

        countries = countryDao.getAllCountries();
        towns = townDao.getTowns();
        System.out.println(countries);
        System.out.println(towns);

        countryDao.delete(slovakia);

        countries = countryDao.getAllCountries();
        towns = townDao.getTowns();
        System.out.println(countries);
        System.out.println(towns);

    }
}
