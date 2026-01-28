package com.li64.tide.data;

import com.li64.tide.Tide;

import java.util.Arrays;
import java.util.List;

public interface ModAssociatedEntry extends ValidatableDataEntry {
    List<String> associatedMods();

    @Override
    default boolean isValid() {
        if (this.associatedMods().isEmpty()) return true;
        return this.associatedMods().stream().allMatch(id -> Tide.PLATFORM.isModLoaded(id)
                || Tide.PLATFORM.isModLoaded(id.replace("-", "_")));
    }

    @Override
    default String invalidReason() {
        return "one or more mods from association list '" + Arrays.toString(this.associatedMods().toArray()) + "' are not loaded";
    }
}
