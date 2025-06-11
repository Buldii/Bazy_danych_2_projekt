package com.game.config;

import com.game.model.BuildingType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBuildingTypeConverter implements Converter<String, BuildingType> {

    @Override
    public BuildingType convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }

        try {
            return BuildingType.valueOf(source.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            try {
                return BuildingType.fromDisplayName(source);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Cannot convert '" + source + "' to BuildingType. " +
                    "Valid values are: TOWN_HALL, BARRACKS or 'Town Hall', 'Barracks'", ex);
            }
        }
    }
}
