// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.9;

/*
1. Write a smart contract which does the following things -
    a) Can take deposits from anyone.
    b) Withdrawals allowed as all-or-nothing. A certain amount can be only withdrawn by
    the person who deposited it.

    For eg- If Bob deposits 3ETH and Alice deposits 2.3ETH then, Bob can withdraw
    ONLY 3ETH as a whole and Alice can ONLY withdraw 2.3ETH as a whole.
*/

contract Account {
    address public owner;
    uint balance;

    // storing the transactions made from each address and the amount deposited
    mapping(address => uint) transactions;

    constructor() {
        owner = msg.sender;
        balance = 0;
    }

    function deposit(address _from, uint _amount) external {
        require(_from != owner, "cannot deposit money to yourself");
        require(_amount > 0, "amount cannot be less than 0");
        balance += _amount;
        transactions[_from] += _amount;
    }

    function getBalance() public view returns(uint) {
        return balance;
    }

    function withdraw() public {
        require(balance > 0, "balance should be greater than 0");
        // uint amountToWithdraw = balance;
        // payable(owner).transfer(amountToWithdraw);
        // transactions[owner] += amountToWithdraw;
        payable(msg.sender).transfer(address(this).balance);
    }
}