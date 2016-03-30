/*****************************************************************************************************************************************/
/* Programma che sfutta la programmazione multi-thread per il calcolo dei primi n numeri interi con race condition tra 2 identici thread */
/*****************************************************************************************************************************************/

/* Stesso esercizio di prima ma con race condizion tra 2 thread identici */
/* Usa questo comando per compilare gcc -ansi pthread_sum.c -o pthread_sum -lpthread */

#include <stdio.h>

/* Includiamo la libreria per usare thread
   posix (linux,solaris,osx   i.e. Unix) */
#include <pthread.h>

/* Dichiarazione della variabile condivisa tra i thread */
/* le variabili globali sono nativamente condivise tra i thread */

int sum;

/* Dichiarazione della funzione che sarà
   il comportamente del thread figlio */
void *runner(void *param);

/* Implementazione della funzione main */
int main (int argc, char *argv[])
{
  /* Dichiarazione delle strutture che
     ci serviranno per gestire i thread */
  pthread_t tid1; /* identificativo univoco del thread 1 */
  pthread_t tid2; /* identificativo univoco del thread 2 */
  pthread_attr_t attr; /* struttura per settare gli
                          attributi del thread */
  
  /* Controllo gli argomenti di lancio */
  /* Il 1 argomento é l'eseguibile, quindi, con 2 avremo 1 argomento di lancio */
  if (argc != 2)
  {
    printf ("uso: pthread-sum <int>\n");
    return 1;
  }
  
  /* Controllo sul tipo di argomento */
  if (atoi (argv[1]) <= 0)
  {
    printf ("Argomento deve essere intero positivo > 0\n");
    return 2;
  }
  
  /* Ora posso creare il thread e lanciarne l'esecuzione */
  /* Inizializzo gli attributi del thread */
  pthread_attr_init (&attr);
  
  /* Posso creare e lanciare il thread 1 */
  pthread_create (&tid1,
                  &attr,
                  runner,
                  argv[1]);

  /* Posso creare e lanciare il thread 2 */
  pthread_create (&tid2,
                  &attr,
                  runner,
                  argv[1]);
  
  /* Metto il padre in attesa per la terminazione del thread */
  /* Questa chiamata é bloccante fino a quando la funzione runner non termina */
  pthread_join (tid1,
                NULL);
  /* Questa chiamata é bloccante fino a quando la funzione runner non termina */
  pthread_join (tid2,
                NULL);
  
  /* Stampo il valore della variabile condivisa */
  printf ("Valore di sum %d\n",
          sum);
  return 0;
}

void *runner(void *param)
{
  /* Faccio la somma dei primi (param) numeri interi */
  int i,
      upper;
  
  upper = atoi (param);
  sum = 0;
  
  /* Eseguo la somma */
  for (i = 1;
       i <= upper;
       i++)
    sum += i;
  
  pthread_exit(0);
}
