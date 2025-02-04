package io.github.nevemlaci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GregtechRecipe {
    static Map<String, Integer> numberOfRecipesWithSameOutput = new HashMap<>();
    List<String> itemInputs;
    List<String> fluidInputs;
    List<String> itemOutputs;
    List<String> fluidOutputs;
    int duration;
    int euT;

    public GregtechRecipe(List<String> itemInputs, List<String> fluidInputs, List<String> itemOutputs, List<String> fluidOutputs, int duration, int euT) {
        this.itemInputs = new ArrayList<>(itemInputs);
        this.itemInputs.removeIf(String::isEmpty);
        this.fluidInputs = new ArrayList<>(fluidInputs);
        this.fluidInputs.removeIf(String::isEmpty);
        this.itemOutputs = new ArrayList<>(itemOutputs);
        this.itemOutputs.removeIf(String::isEmpty);
        this.fluidOutputs = new ArrayList<>(fluidOutputs);
        this.fluidOutputs.removeIf(String::isEmpty);
        this.duration = duration;
        this.euT = euT;
    }

    private String getRecipeId(){
        if(!numberOfRecipesWithSameOutput.containsKey(itemOutputs.getFirst())){
            String ret = itemOutputs.getFirst() + "_0";
            numberOfRecipesWithSameOutput.put(itemOutputs.getFirst(), 1);
            return ret;
        }
        String ret = itemOutputs.getFirst() + "_" + numberOfRecipesWithSameOutput.get(itemOutputs.getFirst());
        numberOfRecipesWithSameOutput.put(itemOutputs.getFirst(), numberOfRecipesWithSameOutput.get(itemOutputs.getFirst()) + 1);
        return ret;
    }

    public String toKubeJS(String recipeType){
        return
            JSHelper.JSFunction("ServerEvents.recipes",
                    JSHelper.JSLambda(List.of("event"),
                            JSHelper.JSFunction("event.recipes.gtceu." + recipeType, JSHelper.JSString(getRecipeId()))
                                    + JSHelper.NullJSMember("itemInputs", itemInputs.stream().map(JSHelper::JSString).toList())
                                    + JSHelper.NullJSMember("inputFluids", fluidInputs.stream().map(JSHelper::JSString).toList())
                                    + JSHelper.NullJSMember("itemOutputs", itemOutputs.stream().map(JSHelper::JSString).toList())
                                    + JSHelper.NullJSMember("outputFluids", fluidOutputs.stream().map(JSHelper::JSString).toList())
                                    + JSHelper.JSMember("duration", String.valueOf(duration))
                                    + JSHelper.JSMember("EUt", String.valueOf(euT))
                    )
            );
    }
}
