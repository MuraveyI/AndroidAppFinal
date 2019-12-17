package com.murav.coffeeapp.repository;

import com.murav.coffeeapp.model.User;

import io.realm.Realm;

public class UserRepositoyrImpl implements UserRepository {
    private Realm mRealm;


    @Override
    public void save(final User user) {
        try {
            mRealm = Realm.getDefaultInstance();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(user);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll() {
        try {
            mRealm = Realm.getDefaultInstance();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.deleteAll();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getOneUser() {
        mRealm = Realm.getDefaultInstance();

        mRealm.beginTransaction();
        User user = mRealm.where(User.class).findFirst();
        mRealm.commitTransaction();

        return user;
    }

    @Override
    public int getNextUserId() {
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        Number id = mRealm.where(User.class).max("id");
        mRealm.commitTransaction();

        return (id == null) ? 1 : id.intValue() + 1;
    }


}
