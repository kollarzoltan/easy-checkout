# Exercise

A store has the following products:

``` 
Code         |  Price
---------------------
VOUCHER      |  5.00€
TSHIRT       | 20.00€
MUG          |  7.50€
```

Various departments have insisted on the following discounts:

 * The marketing department believes in 2-for-1 promotions (buy two of the same product, get one free), and would like for there to be a 2-for-1 special on `VOUCHER` items.

 * The CFO insists that the best way to increase sales is with discounts on bulk purchases (buying x or more of a product, the price of that product is reduced), and demands that if you buy 3 or more `TSHIRT` items, the price per unit should be 19.00€.

The checkout process should apply the discount policies, for example:

    Items: VOUCHER, TSHIRT, MUG
    Total: 32.50€
    
    Items: VOUCHER, TSHIRT, VOUCHER
    Total: 25.00€
    
    Items: TSHIRT, TSHIRT, TSHIRT, VOUCHER, TSHIRT
    Total: 81.00€
    
    Items: VOUCHER, TSHIRT, VOUCHER, VOUCHER, MUG, TSHIRT, TSHIRT
    Total: 74.50€

**The code should:**

- Be written as production-ready code.
- Be easy to grow and easy to add new functionality.
- Have notes attached, explaining the solution and why certain things are included and others are left out.
- Be as simple as possible: clean, minimal, well-designed code; no points for showing off.

## Solution

The solution is provided in _Scala_ using _Sbt_.

To compile, test and run the project, please use the following commands:
```bash
$ sbt compile # Compile the project
$ sbt test # Run auto-tests
$ sbt run # Run the project's example main function
```

### High-level implementation overview

The solution uses the Strategy Design Pattern to implement different pricing and discounting policies.

Property-based testing ensures the code quality.

