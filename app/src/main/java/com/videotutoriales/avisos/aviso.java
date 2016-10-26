package com.videotutoriales.avisos;



public class Aviso {
    private int mId;
    private String mContent;
    private int mImportant;

    public Aviso(int id, String content, int important){

        mId = id; // numero unico identificar cada Aviso
        mImportant = important; //importante o no (1 o 0)
        mContent = content; // contenido


    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getImportant() {
        return mImportant;
    }

    public void setImportant(int important) {
        mImportant = important;
    }
}
