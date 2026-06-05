package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsCorrectly() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientToList() {

        burger.addIngredient(ingredient1);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientFromList() {

        burger.addIngredient(ingredient1);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientCorrectly() {

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0,1);

        assertEquals(ingredient2, burger.ingredients.get(0));

        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnCorrectPrice() {

        when(bun.getPrice()).thenReturn(100f);

        when(ingredient1.getPrice()).thenReturn(50f);

        when(ingredient2.getPrice()).thenReturn(25f);

        burger.setBuns(bun);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float actualPrice = burger.getPrice();

        assertEquals(275f, actualPrice, 0.001f);
    }

    @Test
    public void getReceiptReturnCorrectReceipt() {

        when(bun.getName()).thenReturn("black bun");

        when(bun.getPrice()).thenReturn(100f);

        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);

        when(ingredient1.getName()).thenReturn("cutlet");

        when(ingredient1.getPrice()).thenReturn(50f);

        burger.setBuns(bun);

        burger.addIngredient(ingredient1);

        String expected = String.format("(==== black bun ====)%n" + "= filling cutlet =%n" + "(==== black bun ====)%n" + "%nPrice: %f%n", 250f);

        assertEquals(expected,burger.getReceipt());
    }
}
