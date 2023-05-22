// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.1;

contract CampaignFactory {
    address[] public deployedCampaigns;

    function createCampaign(uint256 minimum) public {
        address newCampaign = address(new Campaign(minimum, msg.sender));
        deployedCampaigns.push(newCampaign);
    }

    function getDeployedCampaigns() public view returns (address[] memory) {
        return deployedCampaigns;
    }
}

contract Campaign {
    struct Request {
        string description;
        uint256 value;
        address payable recipient;
        bool complete;
        uint256 approvalCount;
        // all the contributors who have approved of the request
        mapping(address => bool) approvals;
    }

    // list of requests the manager has created (like request for withdrawal of money for purchasing of raw materials)
    Request[] public requests;
    // owner of the campaign
    address public manager;
    // minimum contribution amount to become a contributor
    uint256 public minimumContribution;
    // list of contributors who have donated the money
    mapping(address => bool) public approvers;
    // total number of contributors
    uint256 public approversCount;
    // total funds received
    uint256 public totalFundedAmount;
    // total funds deposited by each contributor
    mapping(address => uint256) public contributors;

    modifier restricted() {
        require(msg.sender == manager);
        _;
    }

    constructor(uint256 minimum, address creator) {
        manager = creator;
        minimumContribution = minimum;
        approversCount = 0;
        totalFundedAmount = 0;
    }

    // donate money to the campaign
    function contribute() public payable {
        require(msg.value >= minimumContribution);
        approvers[msg.sender] = true;
        contributors[msg.sender] = msg.value;
        totalFundedAmount += msg.value;
        approversCount++;
    }

    // called by the manager to create a spending request
    function createRequest(
        string memory description,
        uint256 value,
        address payable recipient
    ) public restricted {
        require(value <= totalFundedAmount);

        // Create a new storage pointer to the next slot in the requests array
        Request storage newRequest = requests.push();

        // Assign values to the properties of the newRequest struct
        newRequest.description = description;
        newRequest.value = value;
        newRequest.recipient = recipient;
        newRequest.complete = false;
        newRequest.approvalCount = 0;
    }

    // approves the pending request
    function approveRequest(uint256 index) public {
        Request storage request = requests[index];

        require(approvers[msg.sender]);
        require(!request.approvals[msg.sender]);

        request.approvals[msg.sender] = true;
        request.approvalCount++;
    }

    // finalize request when approval rate > 50%
    function finalizeRequest(uint256 index) public restricted {
        Request storage request = requests[index];

        require(request.value <= totalFundedAmount);
        require(request.approvalCount > (approversCount / 2));
        require(!request.complete);

        request.recipient.transfer(request.value);
        totalFundedAmount -= request.value;
        request.complete = true;
    }

    // withdraw the funded amount
    function withdrawFunds(uint256 amount) public {
        address payable recipient = payable(msg.sender);
        require(recipient != manager);
        require(contributors[recipient] >= amount && amount <= totalFundedAmount);
        recipient.transfer(amount);
        contributors[recipient] -= amount;
        totalFundedAmount -= amount;
    }

    function getRequestsCount() public view returns (uint256) {
        return requests.length;
    }

    function getSummary()
        public
        view
        returns (
            uint256,
            uint256,
            uint256,
            uint256,
            address
        )
    {
        return (
            minimumContribution,
            totalFundedAmount,
            requests.length,
            approversCount,
            manager
        );
    }
}
