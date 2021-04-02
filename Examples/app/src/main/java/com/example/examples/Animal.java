package com.example.examples;

public class Animal {

    private long id; // transient - не надо добавлять в json при конвертации

    private String name;
    private String breed;
    private String age;
    private String photoPath;
    private String lat;
    private String lon;
    private String typeAnimal;
    private String owner;

    @Override
    public String toString(){
        return "Input animal info: \n"+name+", "+ breed+", "+ age+", "+ photoPath+", "+
                lat+"\n"+ lon+", "+ typeAnimal+", "+ owner;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPhotoPath() {
        return photoPath;
    }
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getTypeAnimal() {
        return typeAnimal;
    }
    public void setTypeAnimal(String typeAnimal) {
        this.typeAnimal = typeAnimal;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
