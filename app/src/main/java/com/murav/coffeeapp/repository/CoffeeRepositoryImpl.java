
package com.murav.coffeeapp.repository;


import com.murav.coffeeapp.model.CoffeeModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CoffeeRepositoryImpl implements CoffeeRepository {

    private Realm mRealm;

    public CoffeeRepositoryImpl(Realm mRealm) {
        this.mRealm = mRealm;
    }

    public CoffeeRepositoryImpl() {
    }


    @Override
    public void addCoffee(final CoffeeModel coffeeModel) {

        try {
            mRealm = Realm.getDefaultInstance();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(coffeeModel); //save data
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<CoffeeModel> getAllCoffee() {
        List<CoffeeModel> coffeeModels = new ArrayList<>();
        mRealm = Realm.getDefaultInstance();

        mRealm.beginTransaction();
        RealmResults<CoffeeModel> results = mRealm.where(CoffeeModel.class).findAll();
        mRealm.commitTransaction();

        for (CoffeeModel coffeeModel : results) {
            coffeeModels.add(coffeeModel);
            System.out.println(coffeeModel.getId() + " +++ " + coffeeModel.getName() + " +++ " + coffeeModel.getDescription());
        }

        return coffeeModels;
    }

    @Override
    public int getNextCoffeeId() {
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        Number id = mRealm.where(CoffeeModel.class).max("id");
        mRealm.commitTransaction();

        return (id == null) ? 1 : id.intValue() + 1;
    }

    @Override
    public CoffeeModel getById(int id) {
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        CoffeeModel result = mRealm.where(CoffeeModel.class).equalTo("id", id).findFirst();
        mRealm.commitTransaction();

        return result;
    }

    @Override
    public void deleteAll() {

            try {
                mRealm = Realm.getDefaultInstance();
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.delete(CoffeeModel.class);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
