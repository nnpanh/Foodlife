package com.example.foodlife.view_models

import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.DetailDirections
import com.example.foodlife.models.DetailIngredients
import com.example.foodlife.models.DetailReview

class DetailViewModel: ViewModel() {

     var DetailIngredientsList = mutableListOf(
         DetailIngredients("sachet fast-action yeast","7g"),
         DetailIngredients("strong bread flour, plus extra for dusting","500g"),
         DetailIngredients("caster sugar, plus for coating","150g"),
         DetailIngredients("salt","1 tbsp"),
         DetailIngredients("unsalted butter, chilled and cut into cubes","100g"),
         DetailIngredients("large eggs, lightly beaten","2"),
         DetailIngredients("sunflower oil for deep frying, plus extra for greasing","2ltr"),
         DetailIngredients("jar smooth strawberry jam","340g"),
     )

    var DetailIngredientsList2 = mutableListOf(
        DetailIngredients("sachet fast-action yeast","14g"),
        DetailIngredients("strong bread flour, plus extra for dusting","1kg"),
        DetailIngredients("caster sugar, plus for coating","300g"),
        DetailIngredients("salt","2 tbsp"),
        DetailIngredients("unsalted butter, chilled and cut into cubes","100g"),
        DetailIngredients("large eggs, lightly beaten","4"),
        DetailIngredients("sunflower oil for deep frying, plus extra for greasing","4ltr"),
        DetailIngredients("jar smooth strawberry jam","680g"),
    )

    var DetailDirectionsList = mutableListOf(
        DetailDirections(R.drawable.donut1,"Stir 7g yeast into 160ml warm water in a jug, until frothy. Sift 500g strong bread flour, 50g sugar and 1 tsp salt into a bowl. Add 100g chilled and cubed unsalted butter and rub into the dry ingredients with your fingertips until it resembles breadcrumbs. Stir in the yeast liquid and 2 eggs with a wooden spoon to form a dough.","Step 1: Make the dough"),
        DetailDirections(R.drawable.donut2,"Dust a surface with flour. Knead the dough for 10 mins by hand (or in a mixer with a dough hook), until it springs back when pressed. Put the dough in a lightly greased bowl and cover loosely with oiled clingflm. Leave to prove in a warm place for 1-2 hrs, until doubled in size.","Step 2: Knead"),
        DetailDirections(R.drawable.donut3,"Tip out the dough onto a floured surface; knead for 2 mins. Divide into 16 equal pieces and roll into balls. Arrange the dough balls on 2 greased baking trays, spacing them well apart. Cover loosely with oiled clingfilm and set aside to prove for 1-2 hrs, until doubled in size.","Step 3: Shape"),
        DetailDirections(R.drawable.donut4,"Pour 2ltr sunflower oil into a large deep pan and heat to 160°C. If you don’t have a kitchen thermometer, test the temperature by dropping in a cube of bread – if it turns golden in 30 secs, the oil is ready. Working in batches, use a slotted spoon to carefully lower the dough balls into the hot oil.","Step 4: Heat the oil"),
        DetailDirections(R.drawable.donut5,"Fry for 2-3 mins, then carefully turn each ball over and cook for a further 2-3 mins, until golden all over. Remove with the slotted spoon and leave to cool for 5-10 mins on kitchen paper. Repeat with the remaining dough balls, ensuring the oil temperature stays at 160°C.","Step 5: Deep fry"),
        DetailDirections(R.drawable.donut6,"Using a skewer, pierce a hole in each doughnut, wiggling to make space for the filling. Put 340g smooth strawberry jam in a piping bag fitted with a medium nozzle. Working one at a time, insert the nozzle in the hole of each doughnut and gently squeeze to fill with jam. Put 100g caster sugar on a plate or shallow dish and roll the warm doughnuts in the sugar to coat.","Step 6: Fill with jam"),
    )

    var DetailReviewList = mutableListOf(
        DetailReview(R.drawable.example_step_1,"Melanie Rose","Lorem ipsum dolor sit amet, consectetur adipiscing elit!"),
        DetailReview(R.drawable.example_step_2,"Jonathan Jose","Excepteur sint occaecat cupidatat non proident, sunt"),
        DetailReview(R.drawable.ic_user,"Nicky","nisi ut aliquip ex ea commodo consequat."),
        DetailReview(R.drawable.ic_user,"Moon Star","quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea."),
        DetailReview(R.drawable.ic_user,"Melanie","dolor in reprehenderit in voluptate velit esse cillum dolore eu."),
    )
}