package com.hannesdorfmann.annotationprocessing101.factory;

import com.hannesdorfmann.annotationprocessing101.factory.annotation.Factory;


@Factory(
        id = "Latee",
        type = Meal.class
)
public class LateePizza implements Meal {

    @Override
    public float getPrice() {
        return 100.5f;
    }
}