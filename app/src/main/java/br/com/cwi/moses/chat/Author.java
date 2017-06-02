package br.com.cwi.moses.chat;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by Murillo on 02/06/2017.
 */

public class Author implements IUser {

   private String id;
   private String name;
   private String avatar;

    public Author(String id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }
}