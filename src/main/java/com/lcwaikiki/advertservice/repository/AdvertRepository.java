package com.lcwaikiki.advertservice.repository;

import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.model.Advert;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdvertRepository {
    List<Advert> advertList;

    public AdvertRepository() throws ParseException  {
        advertList = new ArrayList<>();

        advertList.add(
                new Advert(1L,"Advert 1", "Summary of advert one",
                        new SimpleDateFormat("dd-MM-yyyy").parse("28-07-2022"),
                        new SimpleDateFormat("dd-MM-yyyy").parse("30-07-2022"),
                        "Intern", 10, "Şişli", "İstanbul", 34,
                        "This is the job definition of advert1", true, "photoURL",
                        "LC Waikiki", "IT", new ArrayList<>()
                        )
        );

        for(long i = 2; i < 10; i++) {
            advertList.add(
                    new Advert(i,"Advert " + i, "Summary of advert "+i,
                            new SimpleDateFormat("dd-MM-yyyy").parse("28-07-2022"),
                            new SimpleDateFormat("dd-MM-yyyy").parse("30-07-2022"),
                            "Intern", 10, "Şişli", "İstanbul", 34,
                            "This is the job definition of advert" + i, true, "photoURL",
                            "LC Waikiki", "IT", new ArrayList<>()
                    )
            );
        }
    }

    public List<Advert> findAll() {
        return advertList;
    }
    public Advert findById(int id) throws AdvertNotFoundException {
        return advertList.stream().filter(advert -> advert.getId()==id).findFirst().orElseThrow(AdvertNotFoundException::new);
    }
    public Advert create(Advert advert) {
        advertList.add(advert);
        return advert;
    }
    public void update(Advert advert, int id) throws AdvertNotFoundException {
        Advert existingAdvert = advertList.stream().filter(ad -> ad.getId() == id).findFirst().orElseThrow(AdvertNotFoundException::new);
        advertList.set(
                advertList.indexOf(existingAdvert),
                advert
        );
    }
    public void delete(int id) {
        advertList.removeIf(advert -> advert.getId()==id);
    }
}
