import csv
import os
from datetime import datetime

FILENAME = "expenses.csv"

# Initialize CSV file if it doesn't exist
if not os.path.exists(FILENAME):
    with open(FILENAME, "w", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["Date", "Category", "Amount"])

def add_expense():
    date = input("Enter date (YYYY-MM-DD) or press Enter for today: ")
    if date.strip() == "":
        date = datetime.now().strftime("%Y-%m-%d")
    category = input("Enter category (Food, Transport, etc.): ")
    amount = float(input("Enter amount: "))

    with open(FILENAME, "a", newline="") as file:
        writer = csv.writer(file)
        writer.writerow([date, category, amount])

    print("✅ Expense added successfully!\n")

def view_expenses():
    if not os.path.exists(FILENAME):
        print("No expenses found!")
        return

    total = 0
    with open(FILENAME, "r") as file:
        reader = csv.reader(file)
        next(reader)  # Skip header
        print("\nDate        | Category       | Amount")
        print("--------------------------------------")
        for row in reader:
            print(f"{row[0]:<12} | {row[1]:<13} | ₹{row[2]}")
            total += float(row[2])
    print("--------------------------------------")
    print(f"Total Spent: ₹{total}\n")

def menu():
    while True:
        print("=== Expense Tracker ===")
        print("1. Add Expense")
        print("2. View Expenses")
        print("3. Exit")
        choice = input("Enter choice: ")

        if choice == "1":
            add_expense()
        elif choice == "2":
            view_expenses()
        elif choice == "3":
            print("Goodbye! 👋")
            break
        else:
            print("Invalid choice. Try again.\n")

if __name__ == "__main__":
    menu()
t