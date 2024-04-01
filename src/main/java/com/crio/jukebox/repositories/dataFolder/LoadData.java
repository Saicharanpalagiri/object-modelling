package com.crio.jukebox.repositories.dataFolder;

import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.exceptions.NoSuchDataException;

public class LoadData {
    private static  Map<String, Data> dataMap = new HashMap();
    public void register(String dataName, Data data) {
        dataMap.put(dataName, data);
    }

    public Data get(String dataName) {
        return dataMap.get(dataName);
    }
    public void executeData(String dataName, String dataPath) throws NoSuchDataException{
        Data data = get(dataName);
        if(data == null){
            throw new NoSuchDataException();
        }
        data.loadData(dataPath, ",");
    }
}
