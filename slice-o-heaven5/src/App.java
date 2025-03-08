import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class PizzaStore {
    // 店铺信息
    private String storeName;
    private String storeAddress;
    private String storeEmail;
    private String storePhone;
    // 菜单信息
    private List<String> storeMenu;
    private Map<String, List<String>> pizzaIngredients;
    private Map<String, Double> pizzaPrice;
    private List<String> sides;
    private List<String> drinks;
    // 订单信息
    private String orderID;
    private double orderTotal;
    private List<Order> orders;


    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;


    public static final List<Long> BLACKLISTED_CARDS = new ArrayList<>();

    static {
        BLACKLISTED_CARDS.add(12345678901234L);
    }


    public PizzaStore() {
        storeName = "Slice-o-Heaven";
        storeAddress = "123 Pizza Street";
        storeEmail = "i***@****************";
        storePhone = "123 - 456 - 7890";

        storeMenu = new ArrayList<>();
        storeMenu.add("Margherita");
        storeMenu.add("Pepperoni");
        storeMenu.add("Hawaiian");

        pizzaIngredients = new HashMap<>();
        List<String> defaultIngredients = new ArrayList<>();
        defaultIngredients.add(DEF_PIZZA_INGREDIENTS);
        for (String pizza : storeMenu) {
            pizzaIngredients.put(pizza, defaultIngredients);
        }

        pizzaPrice = new HashMap<>();
        pizzaPrice.put("Margherita", 10.99);
        pizzaPrice.put("Pepperoni", 12.99);
        pizzaPrice.put("Hawaiian", 13.99);

        sides = new ArrayList<>();
        sides.add("Garlic Bread");
        sides.add("Onion Rings");

        drinks = new ArrayList<>();
        drinks.add("Coke");
        drinks.add("Pepsi");

        orderID = DEF_ORDER_ID;
        orderTotal = DEF_ORDER_TOTAL;
        orders = new ArrayList<>();
    }


    public PizzaStore(String orderID, Map<String, List<String>> pizzaIngredients, double orderTotal) {
        this();
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public List<String> getStoreMenu() {
        return storeMenu;
    }

    public void setStoreMenu(List<String> storeMenu) {
        this.storeMenu = storeMenu;
    }

    public Map<String, List<String>> getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(Map<String, List<String>> pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public Map<String, Double> getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(Map<String, Double> pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public List<String> getSides() {
        return sides;
    }

    public void setSides(List<String> sides) {
        this.sides = sides;
    }

    public List<String> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<String> drinks) {
        this.drinks = drinks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + storeName + "!");


        System.out.println("Available pizza ingredients:");
        System.out.println("1. Tomato Sauce");
        System.out.println("2. Mozzarella Cheese");
        System.out.println("3. Pepperoni");
        System.out.println("4. Ham");
        System.out.println("5. Pineapple");

        List<Integer> ingredientChoices = new ArrayList<>();
        while (ingredientChoices.size() < 3) {
            System.out.print("Choose an ingredient (1 - 5): ");
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= 5) {
                ingredientChoices.add(choice);
            } else {
                System.out.println("Invalid choice. Please choose a number between 1 and 5.");
            }
        }

        String[] ingredientNames = {"Tomato Sauce", "Mozzarella Cheese", "Pepperoni", "Ham", "Pineapple"};
        StringBuilder pizzaIngredientsStr = new StringBuilder();
        for (int choice : ingredientChoices) {
            pizzaIngredientsStr.append(ingredientNames[choice - 1]).append(", ");
        }
        if (pizzaIngredientsStr.length() > 0) {
            pizzaIngredientsStr.delete(pizzaIngredientsStr.length() - 2, pizzaIngredientsStr.length());
        }


        int sizeChoice;
        do {
            System.out.println("Choose pizza size:");
            System.out.println("1. Small");
            System.out.println("2. Medium");
            System.out.println("3. Large");
            System.out.print("Enter your choice (1 - 3): ");
            sizeChoice = scanner.nextInt();
            if (sizeChoice < 1 || sizeChoice > 3) {
                System.out.println("Invalid choice. Please choose a number between 1 and 3.");
            }
        } while (sizeChoice < 1 || sizeChoice > 3);

        String pizzaSize;
        switch (sizeChoice) {
            case 1:
                pizzaSize = "Small";
                break;
            case 2:
                pizzaSize = "Medium";
                break;
            case 3:
                pizzaSize = "Large";
                break;
            default:
                pizzaSize = "Unknown";
        }


        int cheeseChoice;
        do {
            System.out.println("Do you want extra cheese?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter your choice (1 - 2): ");
            cheeseChoice = scanner.nextInt();
            if (cheeseChoice < 1 || cheeseChoice > 2) {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        } while (cheeseChoice < 1 || cheeseChoice > 2);

        String extraCheese;
        switch (cheeseChoice) {
            case 1:
                extraCheese = "Yes";
                break;
            case 2:
                extraCheese = "No";
                break;
            default:
                extraCheese = "Unknown";
        }


        int sideChoice;
        do {
            System.out.println("Choose a side:");
            for (int i = 0; i < sides.size(); i++) {
                System.out.println((i + 1) + ". " + sides.get(i));
            }
            System.out.print("Enter your choice (1 - " + sides.size() + "): ");
            sideChoice = scanner.nextInt();
            if (sideChoice < 1 || sideChoice > sides.size()) {
                System.out.println("Invalid choice. Please choose a valid number.");
            }
        } while (sideChoice < 1 || sideChoice > sides.size());

        String side = sides.get(sideChoice - 1);

    
        int drinkChoice;
        do {
            System.out.println("Choose a drink:");
            for (int i = 0; i < drinks.size(); i++) {
                System.out.println((i + 1) + ". " + drinks.get(i));
            }
            System.out.print("Enter your choice (1 - " + drinks.size() + "): ");
            drinkChoice = scanner.nextInt();
            if (drinkChoice < 1 || drinkChoice > drinks.size()) {
                System.out.println("Invalid choice. Please choose a valid number.");
            }
        } while (drinkChoice < 1 || drinkChoice > drinks.size());

        String drink = drinks.get(drinkChoice - 1);

        System.out.print("Do you want a half - price discount? (yes/no): ");
        scanner.nextLine(); // 消耗换行符
        String discountChoice = scanner.nextLine();

        if ("yes".equalsIgnoreCase(discountChoice)) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }


        double pizzaBasePrice = 10.0;
        double sidePrice = 3.0;
        double drinkPrice = 2.0;
        if ("Large".equals(pizzaSize)) {
            pizzaBasePrice += 5.0;
        } else if ("Medium".equals(pizzaSize)) {
            pizzaBasePrice += 3.0;
        }
        if ("Yes".equals(extraCheese)) {
            pizzaBasePrice += 2.0;
        }
        orderTotal += sidePrice;
        orderTotal += drinkPrice;
        orderTotal += pizzaBasePrice;

        Order order = new Order(orderID, pizzaIngredientsStr.toString(), pizzaSize, extraCheese, side, drink, orderTotal);
        orders.add(order);
        orderID = String.valueOf(Integer.parseInt(orderID.split("-")[2]) + 1);

        System.out.println(this.toString());
    }

  
    public void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        Calendar fiveYearsAgo = Calendar.getInstance();
        fiveYearsAgo.add(Calendar.YEAR, -5);
        Calendar oneHundredTwentyYearsAgo = Calendar.getInstance();
        oneHundredTwentyYearsAgo.add(Calendar.YEAR, -120);

        while (true) {
            System.out.print("Enter your birthday (in yyyy-MM-dd format): ");
            String birthdayInput = scanner.nextLine();
            try {
                birthday = sdf.parse(birthdayInput);
                Calendar birthCal = Calendar.getInstance();
                birthCal.setTime(birthday);
                if (birthCal.after(fiveYearsAgo) || birthCal.before(oneHundredTwentyYearsAgo)) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                } else {
                    break;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        int birthYear = cal.get(Calendar.YEAR);
        int birthMonth = cal.get(Calendar.MONTH);
        int birthDay = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(new Date());
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        int age = currentYear - birthYear;
        if (birthMonth > currentMonth || (birthMonth == currentMonth && birthDay > currentDay)) {
            age--;
        }

        if (age < 18 && birthMonth == currentMonth && birthDay == currentDay) {
            System.out.println("You are eligible for a half - price discount!");
            orderTotal /= 2;
        } else {
            System.out.println("Sorry, you don't meet the criteria for a half - price discount.");
        }
    }


    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        long cardNumber;
        String expiryDate;
        int cvv;

        do {
            System.out.print("Enter card number: ");
            cardNumber = scanner.nextLong();
            scanner.nextLine(); 
        } while (!isValidCardNumber(cardNumber));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        Calendar now = Calendar.getInstance();
        Date currentDate = now.getTime();
        Date expiry;
        do {
            System.out.print("Enter card expiration date (MM/yy): ");
            expiryDate = scanner.nextLine();
            try {
                expiry = sdf.parse(expiryDate);
                if (expiry.before(currentDate)) {
                    System.out.println("The expiration date is in the past. Please enter a future date.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use MM/yy.");
                expiry = null;
            }
        } while (expiry == null || expiry.before(currentDate));

        System.out.print("Enter CVV code: ");
        cvv = scanner.nextInt();

        processCardPayment(cardNumber, expiryDate, cvv);
    }


    private boolean isValidCardNumber(long cardNumber) {
        String cardNumberStr = String.valueOf(cardNumber);
        return cardNumberStr.length() == 14 && !BLACKLISTED_CARDS.contains(cardNumber);
    }


    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = String.valueOf(cardNumber);
        if (cardNumberStr.length() != 14) {
            System.out.println("Invalid card number. Please enter a 14 - digit card number.");
            return;
        }
        if (BLACKLISTED_CARDS.contains(cardNumber)) {
            System.out.println("This card is blacklisted. Payment declined.");
            return;
        }
        System.out.println("Card accepted");

        int firstDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        System.out.println("First digit of the card number: " + firstDigit);

        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(10));
        System.out.println("Last four digits of the card number: " + lastFourDigits);

        String maskedCardNumber = cardNumberStr.substring(0, 4) + "**** ****" + cardNumberStr.substring(10);
        System.out.println("Masked card number: " + maskedCardNumber);
    }


    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder specialInfo = new StringBuilder();
        specialInfo.append("Special of the day:\n");
        specialInfo.append("Pizza: ").append(pizzaOfTheDay).append("\n");
        specialInfo.append("Side: ").append(sideOfTheDay).append("\n");
        specialInfo.append("Price: $").append(specialPrice).append("\n");
        System.out.println(specialInfo.toString());
    }
}