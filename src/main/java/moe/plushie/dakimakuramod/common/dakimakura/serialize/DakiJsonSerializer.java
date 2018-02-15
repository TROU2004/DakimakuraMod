package moe.plushie.dakimakuramod.common.dakimakura.serialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import moe.plushie.dakimakuramod.common.dakimakura.Daki;

public final class DakiJsonSerializer {

    private DakiJsonSerializer() {}
    
    public static JsonElement serialize(Daki src) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("romaji-name", src.getRomajiName());
        jsonObject.addProperty("original-name", src.getOriginalName());
        jsonObject.addProperty("author", src.getAuthor());
        jsonObject.addProperty("image-front", src.getImageFront());
        jsonObject.addProperty("image-back", src.getImageBack());
        return jsonObject;
    }
    
    public static Daki deserialize(String jsonString, String packDirectoryName, String dakiDirectoryName) {
        if (jsonString == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        return deserialize(parser.parse(jsonString), packDirectoryName, dakiDirectoryName); 
    }
    
    public static Daki deserialize(JsonElement json, String packDirectoryName, String dakiDirectoryName) {
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement romajiNameElement = jsonObject.get("romaji-name");
            JsonElement originalNameElement = jsonObject.get("original-name");
            JsonElement authorElement = jsonObject.get("author");
            JsonElement imageFrontElement = jsonObject.get("image-front");
            JsonElement imageBackElement = jsonObject.get("image-back");
            Daki dakimakura = new Daki(packDirectoryName, dakiDirectoryName);
            if (authorElement != null) {
                dakimakura.setAuthor(authorElement.getAsString());
            }
            if (romajiNameElement != null) {
                dakimakura.setRomajiName(romajiNameElement.getAsString());
            }
            if (originalNameElement != null) {
                dakimakura.setOriginalName(originalNameElement.getAsString());
            }
            if (imageFrontElement != null) {
                dakimakura.setImageFront(imageFrontElement.getAsString());
            } else {
                dakimakura.setImageFront("front.png");
            }
            if (imageBackElement != null) {
                dakimakura.setImageBack(imageBackElement.getAsString());
            } else {
                dakimakura.setImageFront("back.png");
            }
            return dakimakura;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
