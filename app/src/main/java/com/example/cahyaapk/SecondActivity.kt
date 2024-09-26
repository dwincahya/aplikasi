package com.example.cahyaapk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Stack

class SecondActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var input: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvResult = findViewById(R.id.tvResult)

        // Number buttons
        setupNumberButton(R.id.btn0, "0")
        setupNumberButton(R.id.btn1, "1")
        setupNumberButton(R.id.btn2, "2")
        setupNumberButton(R.id.btn3, "3")
        setupNumberButton(R.id.btn4, "4")
        setupNumberButton(R.id.btn5, "5")
        setupNumberButton(R.id.btn6, "6")
        setupNumberButton(R.id.btn7, "7")
        setupNumberButton(R.id.btn8, "8")
        setupNumberButton(R.id.btn9, "9")
        setupButton(R.id.btnDot) { appendToInput(".") }

        // Operator buttons
        setupOperatorButton(R.id.btnAdd, "+")
        setupOperatorButton(R.id.btnSubtract, "-")
        setupOperatorButton(R.id.btnMultiply, "X") // Use "X" for multiplication
        setupOperatorButton(R.id.btnDivide, "/")

        // Function buttons
        setupButton(R.id.btnClear) {
            input = ""
            tvResult.text = ""
        }

        setupButton(R.id.btnDelete) {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                tvResult.text = input
            }
        }

        setupButton(R.id.btnEqual) {
            if (input.isNotEmpty()) {
                try {
                    val result = evaluateExpression(input)
                    tvResult.text = result.toString()
                    input = result.toString()
                } catch (e: Exception) {
                    tvResult.text = "Error"
                }
            }
        }

        // Go back button
        val buttonGoBack: Button = findViewById(R.id.kembali)
        buttonGoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    private fun setupNumberButton(buttonId: Int, number: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            appendToInput(number)
        }
    }

    private fun setupOperatorButton(buttonId: Int, op: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            if (input.isNotEmpty() && !input.endsWith(" ")) {
                appendToInput(" $op ")
            }
        }
    }

    private fun setupButton(buttonId: Int, action: () -> Unit) {
        findViewById<Button>(buttonId).setOnClickListener { action() }
    }

    private fun appendToInput(text: String) {
        input += text
        tvResult.text = input
    }

    private fun evaluateExpression(expression: String): Double {
        // Handle parenthesis and operator precedence
        val tokens = tokenize(expression)
        val rpn = infixToPostfix(tokens)
        return evaluateRPN(rpn)
    }

    private fun tokenize(expression: String): List<String> {
        return expression.split(" ").filter { it.isNotEmpty() }
    }

    private fun infixToPostfix(tokens: List<String>): List<String> {
        val precedence = mapOf(
            "+" to 1,
            "-" to 1,
            "X" to 2,
            "/" to 2
        )
        val output = mutableListOf<String>()
        val operators = Stack<String>()

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token)
                token in precedence -> {
                    while (operators.isNotEmpty() && precedence[token]!! <= precedence[operators.peek()]!!) {
                        output.add(operators.pop())
                    }
                    operators.push(token)
                }
            }
        }

        while (operators.isNotEmpty()) {
            output.add(operators.pop())
        }

        return output
    }

    private fun evaluateRPN(tokens: List<String>): Double {
        val stack = Stack<Double>()

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> stack.push(token.toDouble())
                token in listOf("+", "-", "X", "/") -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    val result = when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "X" -> a * b
                        "/" -> if (b != 0.0) a / b else Double.NaN
                        else -> Double.NaN
                    }
                    stack.push(result)
                }
            }
        }

        return stack.pop()
    }
}
