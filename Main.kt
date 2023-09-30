fun main() {
    val bankLogo = """
        ███████╗███╗   ██╗██╗   ██╗██╗███╗   ██╗███████╗
        ██╔════╝████╗  ██║██║   ██║██║████╗  ██║██╔════╝
        ███████╗██╔██╗ ██║██║   ██║██║██╔██╗ ██║███████╗
        ╚════██║██║╚██╗██║╚██╗ ██╔╝██║██║╚██╗██║╚════██║
        ███████║██║ ╚████║ ╚████╔╝ ██║██║ ╚████║███████║
        ╚══════╝╚═╝  ╚═══╝  ╚═══╝  ╚═╝╚═╝  ╚═══╝╚══════╝
    """.trimIndent()

    println("==============================================")
    println(bankLogo)
    println("==============================================")
    println("Welcome to Your Creative Banking System")
    println("==============================================")
    println("Choose an Account Type:")
    println("==============================================")
    println("|  Option  |      Account Type      |")
    println("|=========|=========================|")
    println("|    1    |      Debit account      |")
    println("|    2    |      Credit account     |")
    println("|    3    |      Checking account   |")
    println("==============================================")

    var accountType = ""
    var userChoice = 0
    var accountBalance = (1..1000).random()

    fun withdraw(amount: Int): Int {
        val newBalance = accountBalance - amount
        accountBalance = newBalance
        return newBalance
    }

    fun deposit(amount: Int): Int {
        val newBalance = accountBalance + amount
        accountBalance = newBalance
        return newBalance
    }

    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The checking balance is $accountBalance dollars.")
            return 0
        } else {
            val withdrawnAmount = withdraw(amount)
            return withdrawnAmount
        }
    }

    fun creditDeposit(amount: Int): Int {
        if (accountBalance == 0) {
            println("You don't need to deposit anything; the credit account is already paid off.")
            return 0
        } else if (accountBalance + amount <= 0) {
            println("Deposit failed. You tried to pay off an amount greater than the credit balance. The checking balance is $accountBalance dollars.")
            return 0
        } else if (accountBalance + amount > 0 && accountBalance < 0) {
            println("You deposited just enough money to completely pay off the account.")
            return amount
        } else {
            val depositedAmount = deposit(amount)
            return depositedAmount
        }
    }

    fun transfer(mode: String) {
        val transferAmount: Int

        when (mode) {
            "withdraw" -> {
                if (accountType == "debit") {
                    transferAmount = debitWithdraw(accountBalance)
                } else {
                    transferAmount = withdraw(accountBalance)
                }
                println("The amount you withdrew is $$transferAmount dollars.")
            }
            "deposit" -> {
                if (accountType == "credit") {
                    transferAmount = creditDeposit(accountBalance)
                } else {
                    transferAmount = deposit(accountBalance)
                }
                println("The amount you deposited is $$transferAmount dollars.")
            }
            else -> {
                transferAmount = 0
                println("Invalid mode: $mode")
            }
        }
    }

    while (accountType == "") {
        print("Enter your choice (1, 2, or 3): ")
        userChoice = readLine()?.toIntOrNull() ?: 0

        if (userChoice in 1..3) {
            accountType = when (userChoice) {
                1 -> "Debit"
                2 -> "Credit"
                3 -> "Checking"
                else -> ""
            }
        } else {
            println("Invalid choice. Please select 1, 2, or 3.")
        }
    }

    println("==============================================")
    println("You have created a ${accountType} account.")
    println("==============================================")

    var isSystemOpen = true
    while (isSystemOpen) {
        println("\nWhat would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the app")
        print("Enter your choice (1, 2, 3, or 4): ")

        val userChoice = readLine()?.toIntOrNull() ?: 0

        when (userChoice) {
            1 -> {
                println("Bank account balance: $$accountBalance")
            }
            2 -> {
                transfer("withdraw")
            }
            3 -> {
                transfer("deposit")
            }
            4 -> {
                isSystemOpen = false
                println("==============================================")
                println("Thank you for using Your Creative Banking System")
                println("==============================================")
            }
            else -> {
                println("Invalid choice. Please select 1, 2, 3, or 4.")
            }
        }
    }
}
