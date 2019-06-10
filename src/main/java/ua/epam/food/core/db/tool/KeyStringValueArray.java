package ua.epam.food.core.db.tool;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class KeyStringValueArray {
    private String fields;
    private String questionsSigns;
    private Object[] values;

    public static KeyStringValueArray convert(Map<String,Object> data){
        KeyStringValueArray result = new KeyStringValueArray();
        List<String> keys = new ArrayList<>(data.keySet());
        result.setValues(new Object[keys.size()]);
        for (int keyNum = 0; keyNum < keys.size(); keyNum++) {
            result.values[keyNum] = data.get(keys.get(keyNum));
        }
        result.setQuestionsSigns(getLineOfQs(data.size()));
        result.setFields(joinFieldNames(keys));
        return result;
    }

    public static String getLineOfQs(int num) {
        // Joiner and Iterables from the Guava library
        return Joiner.on(", ").join(Iterables.limit(Iterables.cycle("?"), num));
    }

    public static String joinFieldNames(List<String> fields){
        return Joiner.on(", ").join(fields);
    }
}
