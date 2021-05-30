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

- Dividir o campo em 3 zonas:
	- Defesa
	- Medio
	- Ataque
- Manter o registo de quem tem a posse de bola
- Calculo do que acontecerá a seguir:
	- Acontecimentos
		- Passe para a mesma zona
		- Passe para outra zona
		- Oportunidade de golo
	- Resultados
		- Manter posse de bola
		- Troca de posse de bola
		- Golo 


---

## Leitura do logs

- Para os atributos que não se encontrem no ficheiro de logs, dar um valor aleatório consoante os restantes atributos


