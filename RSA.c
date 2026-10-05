#include <stdio.h>

// 1) Implement recursive GCD function
int gcd(int a, int b){
    if(b==0){
        return a;
    }
    return gcd(b, a % b); //Recursive call to find GCD
}

// 2) Implement modular inverse function
int inverse_mod(int e, int phi){
    int d;
    for(d=1; d < phi; d++){ 
        if((e*d) % phi ==1){
            return d;
        }
    }
    return -1; // No Inverse Found
}

// 3) Implement RSA key generation function
void generate_keys(int p, int q, int e, int *n, int *d){
    //Step 1: Calculate n
     *n = p * q;

     //Step 2: Calculate the totient phi(n)
    int phi = (p-1) * (q-1);

    //Step 3: Verify that e is valid (e < phi and coprime with phi)
    if(e >= phi || gcd(e, phi) !=1){
        printf("Error: Invalid public exponent e.\n");
        return;
    }

    //Step 4: Caluclate the private key exponent d
    *d = inverse_mod(e, phi);

    //Step 5: Print the generated keys
    printf("Public Key: (%d, %d)\n", e, *n);
    printf("Private Key: (%d, %d)\n", *d, *n);

}

// Helper: Modular Exponentiation for large powers
int modExp(int base, int exp, int mod){
    int result = 1;
    base = base % mod;
    for(int i = 0 ; i < exp; i++){
        result = (result * base) % mod;
    }
    return result;
}

// 4) Implement RSA encryption function
int encrypt(int m, int e, int n){
    return modExp(m, e, n);
}

// 5) Implenent RSA decrytion function
int decrypt(int c, int d, int n){
    return modExp(c, d, n);
}

int main(){
    int p, q, e;
    int n, d;
    int message;

    printf("Enter prime p: ");
    scanf("%d", &p);

    printf("Enter prime q: ");
    scanf("%d", &q);

    printf("Enter public exponent e: ");
    scanf("%d", &e);

    printf("Enter message (integer) to encrypt: ");
    scanf("%d", &message);

    printf("\n--- Generating Keys ---\n");
    generate_keys(p, q, e, &n, &d);

    if(d != -1){
        printf("\n--- Testing Encryption & Decryption ---\n");
        int cipher = encrypt(message, e, n);
        printf("Original Message (m): %d\n", message);
        printf("Encrypted Message (c): %d\n", cipher);

        int decrypted_message = decrypt(cipher, d, n);
        printf("Decrypted Message: %d\n", decrypted_message);
    }
    else{
        printf("\nFailed to generate valid keys. Cannot proceed with encryption.\n");
    }
    return 0;
}