package com.example.test5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    Node top;

    public Stack() {
        this.top = null;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) {
            return -1;
        }

        int data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class MainActivity extends AppCompatActivity {
    private EditText etInfix;
    private Button btnSwap;
    private TextView tvPostfix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInfix = findViewById(R.id.et_infix);
        btnSwap = findViewById(R.id.btn_swap);
        tvPostfix = findViewById(R.id.tv_postfix);

        btnSwap.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String infix = etInfix.getText().toString();
                String postfix = InfixtoPostfix(infix);
                tvPostfix.setText(postfix);
            }
        }));
    }

    private String InfixtoPostfix(String infix) {
        Stack stack = new Stack();
        String postfix = "Biểu thức dạng hậu tố: ";

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            char c1 = infix.charAt(i++);

            if (!Character.isDigit(c) && c != '+' && c != '-' && c != '*' && c != '/' && c != '^' && c != '(' && c != ')') {
                return postfix = "Dữ liệu đầu vào không phải là biểu thức";
            }
            if (c == '+' && (c1 == '+') || c == '+' && (c1 == '-') || c == '+' && (c1 == '*') || c == '+' && (c1 == '/') || c == '+' && (c1 == '^')) {
                return postfix = "Dữ liệu đầu vào không phải là biểu thức";
            }

            if (c == '-' && (c1 == '+') || c == '-' && (c1 == '-') || c == '-' && (c1 == '*') || c == '-' && (c1 == '/') || c == '-' && (c1 == '^')) {
                return postfix = "Dữ liệu đầu vào không phải là biểu thức";
            }

            if (c == '*' && (c1 == '+') || c == '*' && (c1 == '-') || c == '*' && (c1 == '*') || c == '*' && (c1 == '/') || c == '*' && (c1 == '^')) {
                return postfix = "Dữ liệu đầu vào không phải là biểu thức";
            }

            if (c == '/' && (c1 == '+') || c == '/' && (c1 == '-') || c == '/' && (c1 == '*') || c == '/' && (c1 == '/') || c == '/' && (c1 == '^')) {
                return postfix = "Dữ liệu đầu vào không phải là biểu thức";
            }

            if (c == '^' && (c1 == '+') || c == '^' && (c1 == '-') || c == '^' && (c1 == '*') || c == '^' && (c1 == '/') || c == '^' && (c1 == '^')) {
                return postfix = "Dữ liệu đầu vào không phải là biểu thức";
            }
        }

        if (infix.length() == 0) {
            return postfix = "Dữ liệu trống!";
        }

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c)) {
                postfix += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.top != null && stack.top.data != '(') {
                    postfix += Character.toChars(stack.pop())[0];
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence((char) stack.top.data) >= precedence(c)) {
                    postfix += Character.toChars(stack.pop())[0];
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix += Character.toChars(stack.pop())[0];
        }

        return postfix;
    }

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

}