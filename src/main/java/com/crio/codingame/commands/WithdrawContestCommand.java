package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.services.IUserService;

public class WithdrawContestCommand implements ICommand{

    private final IUserService userService;
    
    public WithdrawContestCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute withdrawContest method of IUserService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["WITHDRAW_CONTEST","3","Joey"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        String contestId=tokens.get(1);
        String userName=tokens.get(2);
        try {
            UserRegistrationDto userRegistrationDto=userService.withdrawContest(contestId, userName);
            System.out.println(userRegistrationDto);
        } catch (RuntimeException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
}
