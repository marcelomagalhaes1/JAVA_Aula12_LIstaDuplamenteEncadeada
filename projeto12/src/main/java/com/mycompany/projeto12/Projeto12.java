/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto12;
import java.util.Random;

/**
 *
 * @author Marcelo
 */
class Celula {
    int elemento;
    Celula anterior;
    Celula proxima;

    public Celula(int elemento) {
        this.elemento = elemento;
        this.anterior = null;
        this.proxima = null;
    }
}

class DoublyLinkedList {
    Celula inicio;
    Celula fim;

    public void inserirOrdenado(int elemento) {
        Celula novaCelula = new Celula(elemento);

        if (inicio == null) {
            inicio = novaCelula;
            fim = novaCelula;
        } else if (elemento <= inicio.elemento) {
            novaCelula.proxima = inicio;
            inicio.anterior = novaCelula;
            inicio = novaCelula;
        } else {
            Celula atual = inicio;
            while (atual.proxima != null && elemento > atual.proxima.elemento) {
                atual = atual.proxima;
            }

            novaCelula.proxima = atual.proxima;
            novaCelula.anterior = atual;
            if (atual.proxima != null) {
                atual.proxima.anterior = novaCelula;
            } else {
                fim = novaCelula;
            }
            atual.proxima = novaCelula;
        }
    }

  
    public void removerPrimos() {
        Celula atual = inicio;

        while (atual != null) {
            int elemento = atual.elemento;

            if (elemento > 1 && isPrimo(elemento)) {
                Celula anterior = atual.anterior;
                Celula proxima = atual.proxima;

                if (anterior != null) {
                    anterior.proxima = proxima;
                } else {
                    inicio = proxima;
                }

                if (proxima != null) {
                    proxima.anterior = anterior;
                } else {
                    fim = anterior;
                }
            }

            atual = atual.proxima;
        }
    }

   
    private boolean isPrimo(int numero) {
        if (numero < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }
}
public class Projeto12 {

    public static void main(String[] args) {
       DoublyLinkedList lista = new DoublyLinkedList();
        Random random = new Random();

      
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(19999) - 9999;
            lista.inserirOrdenado(randomNumber);
        }

       
        System.out.println("Lista em ordem crescente:");
        imprimirLista(lista.inicio);

      
        lista.removerPrimos();

      
        System.out.println("\nLista após remoção de primos (Crescente):");
        imprimirLista(lista.inicio);

   
        System.out.println("\nLista após remoção de primos (Decrescente):");
        imprimirListaReversa(lista.fim);
    }

    private static void imprimirLista(Celula inicio) {
        Celula atual = inicio;
        while (atual != null) {
            System.out.print(atual.elemento + " => ");
            atual = atual.proxima;
        }
        System.out.println("null");
    }

    private static void imprimirListaReversa(Celula fim) {
        Celula atual = fim;
        while (atual != null) {
            System.out.print(atual.elemento + " => ");
            atual = atual.anterior;
        }
        System.out.println("null");
    }
}