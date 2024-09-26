# 2. Implicit driving ports interface

Date: 2024-09-26

## Status

Accepted

## Context

When the language allows the explicit definition of an interface, like in Java, there is the decision on defining the ports implicitly or explicitly.

## Decision

For the driving ports we will use the implicit interface given by the implementing class.

For example `IngredientsCatalog` is at the same time an implementing class and a port definition `ForManagingIngredients`.

```java
public class IngredientsCatalog {
    private ForPersistingIngredients ingredientsRepository;

    public IngredientsCatalog(ForPersistingIngredients ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<Ingredient> getIngredients(int page, int size) {
        return ingredientsRepository.getIngredients(page, size);
    }

    public Ingredient addIngredient(IngredientCreationCommand newIngredient) {
        IngredientsCatalog.validateIngredient(newIngredient);

        if (ingredientsRepository.doesExist(newIngredient.name())) {
            var errorMessage = MessageFormat.format("Ingredient {0} already exists", newIngredient.name());
            throw new AlreadyExistingIngredientException(errorMessage);
        }

        return ingredientsRepository.addIngredient(newIngredient);
    }

    private static void validateIngredient(IngredientCreationCommand newIngredient) {
        var errors = new ArrayList<String>();

        if (newIngredient.name() == null || newIngredient.name().isBlank()) {
            errors.add("Ingredient name is missing");
        }

        if (newIngredient.seasonality() == null || newIngredient.seasonality().size() == 0) {
            errors.add("Seasonality is missing");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
```

![For managing ingredients port definition diagram](../assets/for-managing-ingredients-port.png)

## Consequences

On one side we have simpler code because we avoid defining a useless interface that probably won't have more than one implementation ever.

``` java
public interface ForManagingIngredients {
    List<Ingredient> getIngredients(int page, int size);
    Ingredient addIngredient(IngredientCreationCommand newIngredient);
}
```

```java
public class IngredientsCatalog implements ForManagingIngredients {
    ...
}
```

`IngredientsCatalog` is completely equivalent to the original class, so the explicit interface `ForManagingIngredients` doesn't add any value.

Some problem could arise if in the future a new method is defined as public, when it is not needed as public, and it is erroneously understood as part of the port. But applying good practices that case is very unlikely.
