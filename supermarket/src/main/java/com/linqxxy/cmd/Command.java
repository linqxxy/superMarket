package com.linqxxy.cmd;

import java.util.Scanner;

public interface Command {
    Scanner sc = new Scanner(System.in);

    void execute(Subject subject);
}
