package org.fitness_center.console_UI;

import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Program finished");
        System.exit(0);
    }
}
