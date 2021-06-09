## Jogador (maybe Abstract)
 - Funções Abstract
 	- HabilidadeGeral

### Subclasses (Jogador)

- Avançado 
- Medio 
- Defesa 
- Lateral
	- **TBR** | Add new variable to each player
- Guarda-Redes 

---

## Atributos (Interface):  

### Gerais:

- #### Atacante:
	- remate
	- jogo de cabeça

- #### Defensivo:
	- impulso
	- capacidadeDefensiva

- #### Fisico:
	- velocidade
	- resistencia

- #### Tecnico:
	- capacidadeDePasse
	- destreza

- #### MentalTactico:
	- posicionamento
	- motivação

---

### Especificos:

- ###  Avançado:	***Favorecer Atacante e Fisico***
	- finalização

- ###  Medio: ***Favorecer Tecnico e Fisico***
	- recuperação de bolas
	- visão

- ### Defesa: ***Favorecer Defensivo e MentalTactico***
	- marcação

- ### Lateral: 	***Favorecer Defensivo e Fisico***
	- cruzamentos

- ### GR: ***Favorecer MentalTactico***
	- elasticidade
	- reflexos

---

# Classes | Variáveis

- ## Jogo
	- EquipaJogo Casa
	- EquipaJogo Fora
	- Resultado


- ## EquipaJogo 
	- IdEquipa;
	- EquemaTatico;
	- List (ID | 11 titular) 
		- Usar um par para manter registo do cansaço dos jogadores Pair <ID,Double>  
	- List (ID | Suplentes)
	- List (Triplet  <ID,ID,TIME> | Substituição)



- ## CatalogoEquipas
	- Map <Id,Equipa>; ID ? Name   


- ## Equipa
	- Name;
	- Map <Id,Jogador>; 
		- ID ? Name (can be duplicated) ou Number of Shirt
	- Boolean Lateral;

- ## Jogador
	- Name;
	- ID;
	- Map <Double,List<Atributo<>>;
		- Double determinará o peso que o atributo tem no jogador
	- List Historico;



- ## EquemaTatico ?
	- Possibilidades
		- 4-3-3
		- 4-4-2
		- 5-3-2
		- 4-5-1
	- Permitido:
		- Jogadores de diferentes posições mas com lateralidades iguais | Ex: Avançado-lateral pode jogar a defesa-lateral
		- Jogadores da mesma posição mas com lateralidades diferentes | Ex: Defesa-central pode jogar a defesa-lateral
	- Proibido:
		- Jogadores de diferentes posições e com lateralidades diferentes | Ex: Defesa-central não pode jogar a avançado-lateral



---
# Estratégias para o Jogo

## MomentoJogo 

```java 
public class MomentoJogo{
	int posseDeBola; 			//0 -> CASA | 1 -> FORA
	Int zonaDoCampo; 			//1 -> CASA->defesa , FORA ->ataque | 2-> CASA|FORA ->medio | 3-> CASA->ataque , FORA->defesa
}


enum Acontecimentos{
	PASSE_TO_ZONA_1;
	PASSE_TO_ZONA_2;
	PASSE_TO_ZONA_3;
	OPORTUNIDADE_GOLO;
}
```

### Considerações
	- Passe para a mesma zona 
		- Definir uma probablidade de sucesso consoante a zona do campo e a equipa
			- Média da habilidade geral dos jogadores nessa zona
			- Adicionar a diferença da média geral dos jogadores adversários
		- CASA (ataca de 1 para 3):
			- Sentido cresecente: Probablidade diminui
			- Sentido decrescente: Probablidade aumenta
		- FORA (ataca de 3 para 1):
			- Sentido crescente: Probablidade aumenta
			- Sentido decrescente: Probablidade diminui
	- Oportunidade de golo:
		- Probablidade aumenta consoante o streak de passes ?? (to think about)

```java
public void calculaMomento (Jogo jogo){
	List <Jogadores> jogadoresCasa = jogo.getZona(zonaDoCampo,0);
	List <Jogadores> jogadoresFora = jogo.getZona(zonaDoCampo,1);
	


	int acontecimento = getAcontecimento ();


	double probablidadeDeSuceder =  calculaProb (jogadoresCasa,jogadoresFora); 

	
	boolean resultado = calcularResultado (probablidadeDeSuceder);

	
	jogo.aplicarResultado (acontecimento,resultado);

}


```

## Exemplo de como fazer a probablidade 

```java

double probablidade = 0.5;

int result = rand.nextInt (100);

int number = (int)probalidade * 10;

if (result < number){
	//SUCESSS
}
else {
	//FAILED	
}

```



---

## Leitura do logs

- Para os atributos que não se encontrem no ficheiro de logs, dar um valor aleatório consoante os restantes atributos


---

# Interface Necessário
	- Menu Inicial
		- Simular um jogo
			- Selecionar equipa CASA
				- Vizualizar equipa
				- Escolher Esquema Tático
					- Escolher jogadores para as posições
				- Voltar Atrás
			- Selecionar equipa FORA
				- Vizualizar equipa
				- Escolher Esquema tático
					- Escolher jogadores para as posições
				- Voltar Atrás
		- Ver equipas
			- Selecionar Equipa
				- Selecionar Jogador
					- Transferir Jogador
						- Selecionar equipa
					- Voltar atrás
				- Voltar atrás
			- Voltar Atrás
		- Load File
			-Escolher nome
		- Save File
			- Escolher nome
				- Verificar se existe um ficheiro com esse nome, se existir, perguntar se quer dar overwrite
		- Exit

---

# Probabilidade de Acontecimento

- ## ZONA 1
	- Passe Zona 1:
		Acontecer:**50%**
		Sucesso:**+0%**
	- Passe Zona 2:
		Acontecer:**35%**
		Sucesso:**-15%**
	- Passe Zona 3:
		Acontecer:**14%**
		Sucesso:**-40%**
	- Oportunidade de Golo:
		Acontecer:**1%**
		Sucesso:**-99%**
- ## ZONA 2
	- Passe Zona 1:
		Acontecer:**35%**
		Sucesso:**+10%**
	- Passe Zona 2:
		Acontecer:**35%**
		Sucesso:**-10%**
	- Passe Zona 3:
		Acontecer:**25%**
		Sucesso:**-15%**
	- Oportunidade de Golo:
		Acontecer:**5%**
		Sucesso:**-65%**

- ## ZONA 3
	- Passe Zona 1:
		Acontecer:**10%**
		Sucesso:**-5%**
	- Passe Zona 2:
		Acontecer:**35%**
		Sucesso:**-10%**
	- Passe Zona 3:
		Acontecer:**35%**
		Sucesso:**-20%**
	- Oportunidade de Golo:
		Acontecer:**20%**
		Sucesso:**-10%**

## Método calcular probabilidade de suceder
	1. Média dos jogadores da zona
	2. Adicionar diferença da média dos jogadores adversários da zona
	3. Adicionar probablidade de suceder

