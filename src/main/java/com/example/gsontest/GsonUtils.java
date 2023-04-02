package com.example.gsontest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GsonUtils {
    public static Gson getGson()
    {
        return new GsonBuilder()
                .registerTypeAdapter(new TypeToken<Map<String,Object>>(){}.getType(),new TypeAdapter(){
                    @Override
                    public void write(JsonWriter jsonWriter, Object o) throws IOException {

                    }

                    @Override
                    public Object read(JsonReader jsonReader) throws IOException {
                        JsonToken token = jsonReader.peek();
                        switch (token) {
                            case BEGIN_ARRAY:
                                List<Object> list = new ArrayList<Object>();
                                jsonReader.beginArray();
                                while (jsonReader.hasNext()) {
                                    list.add(read(jsonReader));
                                }
                                jsonReader.endArray();
                                return list;
                            case BEGIN_OBJECT:
                                Map<String, Object> map = new LinkedTreeMap<String, Object>();
                                jsonReader.beginObject();
                                while (jsonReader.hasNext()) {
                                    map.put(jsonReader.nextName(), read(jsonReader));
                                }
                                jsonReader.endObject();
                                return map;

                            case STRING:
                                return jsonReader.nextString();
                            case NUMBER:
                                String s = jsonReader.nextString();
                                if (s.contains(".")) {
                                    return Double.valueOf(s);
                                } else {
                                    try {
                                        return Integer.valueOf(s);
                                    } catch (Exception e) {
                                        return Long.valueOf(s);
                                    }
                                }
                            case BOOLEAN:
                                return jsonReader.nextBoolean();
                            case NULL:
                                jsonReader.nextNull();
                                return null;
                            default:
                                throw new IllegalStateException();
                        }
                    }
                })
                .serializeNulls()
                .create();
    }
}
