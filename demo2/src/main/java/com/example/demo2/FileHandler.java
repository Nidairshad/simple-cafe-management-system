package com.example.demo2;

import java.io.*;

//used in show order when clicked to show inventory
public class FileHandler {

    String[] itemNames = new String[4];
    double[] itemPrice = new double[4];
    int[] itemLeft = new int[4];
    int[] itemPurchased = new int[4];

    int itemCount = 0;


    public static void updateStockAfterOrder(String filePath, String itemName[], int tea,
                                             int latte, int cookie, int crossiant) {

        File file = new File("src/main/resources/com/example/demo2/inventory.txt");

        StringBuilder newContent = new StringBuilder();
        int lineNum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Updating stocks after order placing in your " + file + " file.");

            String header = reader.readLine();
            newContent.append(header).append("\n");

            String line;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                String[] parts = line.split(",");

                if (parts.length < 4) {
                    newContent.append(line).append("\n"); // skip bad lines as-is
                    continue;
                }

                int left = Integer.parseInt(parts[1]); // column for 'left'
                int purchased = Integer.parseInt(parts[2]);//column for 'purchased'add number of items purchased
                switch (lineNum) {
                    case 1: // Tea
                        if (tea > 0) {
                            left = Math.max(0, left - tea);
                            purchased = Math.max(0,purchased + tea);
                            parts[1] = String.valueOf(left);//left value subtracted
                            parts[2] = String.valueOf(purchased);//purchased value added
                        }
                        break;
                    case 2: // Latte
                        if (latte > 0) {
                            left = Math.max(0, left - latte);
                            purchased = Math.max(0,purchased + latte);
                            parts[1] = String.valueOf(left);
                            parts[2] = String.valueOf(purchased);
                        }
                        break;
                    case 3: // Cookie
                        if (cookie > 0) {
                            left = Math.max(0, left - cookie);
                            purchased = Math.max(0,purchased + cookie);
                            parts[1] = String.valueOf(left);
                            parts[2] = String.valueOf(purchased);
                        }
                        break;
                    case 4: // Croissant
                        if (crossiant > 0) {
                            left = Math.max(0, left - crossiant);
                            purchased = Math.max(0,purchased + crossiant);
                            parts[1] = String.valueOf(left);
                            parts[2] = String.valueOf(purchased);
                        }
                        break;
                    default:
                        // nothing to update
                        break;
                }

                // always write the full line back
                newContent.append(String.join(",", parts)).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // now after that write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(newContent.toString());
            System.out.println("Stock updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}



