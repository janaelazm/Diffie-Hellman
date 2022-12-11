# Diffie-Hellman
 
The implementation consists of 3 branches.

main
In branch main there is a sleek architecture: classes Bob and Alice and parent class DHE, instances for these classes are created in Main class.
The result of calculation is printed in console, Bob's result and Alice's result should be the same

socket
In order to added socket functionality the whole structure had to be refactored. 
To run the implementation, firstly run BobServer class, then AliceClient. After program is executed, both classes orinted result in console.
Alice's answer and Bob's answer should be the same.

eve
In this branch we mocked a simple model for Eve (man-im-the-middle) attack. Eve connects in the port 9998 with Bob and in the port 9999 with Alice.
Eve uses fake private key to calculate fake public key for Bob and separate fake public key for Alice. Alice think Eve is Bob, Bob think Eve is Alice. 
Now Eve confirmed its identity by calculate same secret as Bob and same secret as Alice. Now it is able to get messaged from Bob, change it and send to Alice.

Parametres g, n, a and b are hardcoded for simplicity.
My suggestion is to add them in programs arguments in future.
