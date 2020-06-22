package com.javarush.task.task25.task2503;

/* 
Свой enum
*/
public class Solution {
    /**
     * Output:
     * <p/>
     * Available Amount
     * Account Number
     * Bank Name
     * --------------------
     * Available Amount
     * Bank Name
     */
    public static void main(String[] args) {

        Column.configureColumns(Column.Amount, Column.AccountNumber, Column.BankName);

//        for (Column column: Column.values()
//             ) {
//            System.out.println(Column.realOrder[column.ordinal()] + " " + column.isShown());
//        }

        for (Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }

        System.out.println("--------------------");
        Column.AccountNumber.hide();

//        for (Column column: Column.values()
//        ) {
//            System.out.println(Column.realOrder[column.ordinal()] + " " + column.isShown());
//        }

        for (Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }
    }
}
