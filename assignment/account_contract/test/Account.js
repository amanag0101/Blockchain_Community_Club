const { ethers } = require("hardhat");
const { expect } = require("chai");

describe("Token Contract", function() {
    it("should set the initial balance to 0", async function() {
        // the contract that needs to be deployed
        const account = await ethers.getContractFactory("Account");
        const hardhatToken = await account.deploy();

        // get the balance of the owner
        const ownerBalance = await hardhatToken.getBalance();
        
        // verify
        expect(ownerBalance).to.equal(0);
    });

    it("should deposit any amount from any address", async function() {
        // the contract that needs to be deployed
        const account = await ethers.getContractFactory("Account");
        const hardhatToken = await account.deploy();
        
        // deposit
        await hardhatToken.deposit("0x70997970C51812dc3A010C7d01b50e0d17dc79C8", 100);

        // get the balance of the owner
        const ownerBalance = await hardhatToken.getBalance();

        // verify
        expect(ownerBalance).to.equal(100);
    });

    it("should transfer all the balance of the smart contract to the owner's address", async function() {
        // the contract that needs to be deployed
        const account = await ethers.getContractFactory("Account");
        const hardhatToken = await account.deploy();

        // deposit
        await hardhatToken.deposit("0x70997970C51812dc3A010C7d01b50e0d17dc79C8", 100);

        console.log("owner: " + await hardhatToken.owner());
        console.log("Balance: " + await hardhatToken.getBalance());

        // withdraw
        await hardhatToken.withdraw();
        
        // get the balance of the owner
        const ownerBalance = await hardhatToken.getBalance();

        // verify
        expect(ownerBalance).to.equal(100);
    });
});