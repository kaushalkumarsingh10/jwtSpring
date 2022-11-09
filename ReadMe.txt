POST :: localhost:8080/auth

Request:
{
    "username":"kaushal",
    "password":"kaushal"
}

Response : {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXVzaGFsIiwiZXhwIjoxNjY3ODM2NjY1LCJpYXQiOjE2Njc4MzY1NDV9.EuZpoUcOY7KmyvaBjHCg_0umY1fBinMu8c8Njy0AOa4"
}


---------------------------------------------------------------------------------------------------------------------------------------------------

GET :: localhost:8080/Account/getAccountDetails

Header:
    Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXVzaGFsIiwiZXhwIjoxNjY3ODM2NjY1LCJpYXQiOjE2Njc4MzY1NDV9.EuZpoUcOY7KmyvaBjHCg_0umY1fBinMu8c8Njy0AOa4

Response :
[
    {
        "accountNumber": 1,
        "accountName": "Kaushal",
        "emailId": "kaushalkumarsingh10@gmail.com",
        "type": "Current"
    },
    {
        "accountNumber": 2,
        "accountName": "Sudha",
        "emailId": "sudhasingh@gmail.com",
        "type": "Current"
    }

---------------------------------------------------------------------------------------------------------------------------------------------------

POST :: localhost:8080/Account/updateAccount

Header:
    Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXVzaGFsIiwiZXhwIjoxNjY3ODM2NjY1LCJpYXQiOjE2Njc4MzY1NDV9.EuZpoUcOY7KmyvaBjHCg_0umY1fBinMu8c8Njy0AOa4

Request:
    {
        "accountNumber": 1,
        "accountName": "Kaushal",
        "emailId": "kaushalkumarsingh10@gmail.com",
        "type": "Current"
    }

Response:
    {
        "accountNumber": 1,
        "accountName": "Kaushal",
        "emailId": "kaushalkumarsingh10@gmail.com",
        "type": "Current"
    }


---------------------------------------------------------------------------------------------------------------------------------------------------    

GET :: localhost:8080/Account/getAccountDetail?accountNumber=1

Header:
    Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXVzaGFsIiwiZXhwIjoxNjY3ODM2NjY1LCJpYXQiOjE2Njc4MzY1NDV9.EuZpoUcOY7KmyvaBjHCg_0umY1fBinMu8c8Njy0AOa4

Response:
    {
        "accountNumber": 1,
        "accountName": "Kaushal",
        "emailId": "kaushalkumarsingh10@gmail.com",
        "type": "Current"
    }


---------------------------------------------------------------------------------------------------------------------------------------------------

POST :: localhost:8080/Account/createAccount


Header:
    Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXVzaGFsIiwiZXhwIjoxNjY3ODM2NjY1LCJpYXQiOjE2Njc4MzY1NDV9.EuZpoUcOY7KmyvaBjHCg_0umY1fBinMu8c8Njy0AOa4

Request:
    {
        "accountNumber": 3,
        "accountName": "Amit",
        "emailId": "kaushalkumarsingh10@gmail.com",
        "type": "Current"
    }

Response:
    {
        "accountNumber": 3,
        "accountName": "Amit",
        "emailId": "kaushalkumarsingh10@gmail.com",
        "type": "Current"
    }

