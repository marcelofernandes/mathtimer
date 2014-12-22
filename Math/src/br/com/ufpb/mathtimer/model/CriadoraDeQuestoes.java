package br.com.ufpb.mathtimer.model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.com.ufpb.mathtimer.view.R;

public class CriadoraDeQuestoes {
	private static Activity activityAtual;

	public static List<Questao> getListaDeQuestoes(Activity activity) {
		activityAtual = activity;
		List<Questao> questoes = new ArrayList<Questao>();
		Questao[] q = new Questao[32];

//		q[0] = new Questao(
//				"A figura a seguir � uma representa��o da localiza��o das principais "
//						+ "cidades ao longo de uma estrada, onde est� indicada por letras a "
//						+ "posi��o dessas cidades e por n�meros as temperaturas registradas em �C. "
//						+ "Com base na figura e mantendo-se a varia��o de temperatura entre as "
//						+ "cidades, o ponto correspondente a 0 �C estar� localizado");
//		setImagem(R.drawable.imagem1, q[0]);
//		adicionarAlternativas(q[0],
//				new Alternativa(3, "entre os pontos I e J"), new Alternativa(1,
//						"sobre o ponto M"), new Alternativa(2,
//						"entre os pontos L e M"), new Alternativa(3,
//						"entre os pontos I e J"), new Alternativa(4,
//						"sobre o ponto J"));
//
//		q[1] = new Questao(
//				"Em um exame de vista, o m�dico solicitou que o paciente identificasse 2/3 "
//						+ "de bolinhas pretas em rela��o ao total de bolinhas. Qual a figura "
//						+ "identificada pelo paciente?");
//		setImagem(R.drawable.imagem2, q[1]);
//		adicionarAlternativas(q[1], new Alternativa(3, "C"), new Alternativa(1,
//				"A"), new Alternativa(2, "B"), new Alternativa(3, "C"),
//				new Alternativa(4, "D"));
//
//		q[2] = new Questao(
//				"Quatro amigos, Jo�o, Pedro, Ana e Maria sa�ram juntos para fazer um passeio "
//						+ "por um mesmo caminho. At� agora, Jo�o andou 6/8 do caminho; Pedro 9/12; "
//						+ "Ana, 3/8 e Maria 4/6. Os amigos que se encontram no mesmo ponto do caminho s�o ");
//		q[2].setTemImagem(false);
//		adicionarAlternativas(q[2], new Alternativa(1, "Jo�o e Pedro"),
//				new Alternativa(1, "Jo�o e Pedro"), new Alternativa(2,
//						"Jo�o e Ana"), new Alternativa(3, "Ana e Maria"),
//				new Alternativa(4, "Pedro e Ana"));
//
//		q[3] = new Questao(
//				"A figura seguinte, formada por lados iguais, mostra uma etiqueta que deve ser "
//						+ "fixada �s embalagens de determinado produto. O tempo gasto, em segundos, "
//						+ "para cortar essa etiqueta � obtido multiplicando-se por dez a medida do seu "
//						+ "contorno. Assim sendo, o tempo total gasto, em segundos, para essa tarefa �");
//		setImagem(R.drawable.imagem3, q[3]);
//		adicionarAlternativas(q[3], new Alternativa(2, "200"), new Alternativa(
//				1, "180"), new Alternativa(2, "200"),
//				new Alternativa(3, "220"), new Alternativa(4, "240"));
//
//		q[4] = new Questao(
//				"Cristina desenhou quatro pol�gonos regulares, conforme pode ser visto na figura "
//						+ "a seguir, e anotou dentro deles o valor da soma de seus �ngulos internos. Qual "
//						+ "� a medida de cada �ngulo interno do hex�gono regular desenhado por Cristina?");
//		setImagem(R.drawable.imagem4, q[4]);
//		adicionarAlternativas(q[4], new Alternativa(3, "120�"),
//				new Alternativa(1, "60�"), new Alternativa(2, "108�"),
//				new Alternativa(3, "120�"), new Alternativa(4, "135�"));
//
//		q[5] = new Questao(
//				"A evolu��o da inten��o de votos dos eleitores por dois candidatos a prefeito "
//						+ "de um munic�pio � apresentada pelo gr�fico seguinte. Em que m�s o candidato "
//						+ "A alcan�ou, na inten��o de votos dos eleitores, o candidato B?");
//		setImagem(R.drawable.imagem5, q[5]);
//		adicionarAlternativas(q[5], new Alternativa(2, "Agosto"),
//				new Alternativa(1, "Julho"), new Alternativa(2, "Agosto"),
//				new Alternativa(3, "Setembro"), new Alternativa(4, "Outubro"));
//
//		q[6] = new Questao(
//				"Em um jogo de v�lei, os torcedores estavam acomodados em tr�s �reas distintas "
//						+ "do gin�sio, demarcadas por cores diferentes. Na �rea verde havia 21 828 "
//						+ "torcedores, na azul 12 100 e na amarela 32 072. Nesse jogo, apenas 20% do "
//						+ "total de torcedores presentes no gin�sio torciam pelo time que venceu a "
//						+ "partida. Qual � o n�mero de torcedores que torciam pelo time vencedor?");
//		q[6].setTemImagem(false);
//		adicionarAlternativas(q[6], new Alternativa(4, "13200"),
//				new Alternativa(1, "2420"), new Alternativa(2, "4365"),
//				new Alternativa(3, "6414"), new Alternativa(4, "13200"));
//
//		q[7] = new Questao(
//				"Uma torneira com problemas continua pingando mesmo depois de fechada, "
//						+ "desperdi�ando em uma hora 125 mL de �gua. Quantos litros de �gua "
//						+ "desperdi�ar� em 24 horas?");
//		q[7].setTemImagem(false);
//		adicionarAlternativas(q[7], new Alternativa(2, "3,0"), new Alternativa(
//				1, "1,5"), new Alternativa(2, "3,0"),
//				new Alternativa(3, "15,0"), new Alternativa(4, "30,0"));
//
//		q[8] = new Questao(
//				"A figura a seguir representa um mapa bastante simplificado de uma cidade, "
//						+ "em que est�o marcados alguns de seus pontos de interesse. Nesse mapa, a "
//						+ "coordenada (5,G) indica a localiza��o");
//		setImagem(R.drawable.imagem6, q[8]);
//		adicionarAlternativas(q[8], new Alternativa(4, "do cinema"),
//				new Alternativa(1, "da catedral"), new Alternativa(2,
//						"da quadra poliesportiva"), new Alternativa(3,
//						"do teatro"), new Alternativa(4, "do cinema"));
//
//		q[9] = new Questao(
//				"As figuras mostradas a seguir est�o organizadas dentro de um padr�o que "
//						+ "se repete. Mantendo essa disposi��o, a express�o alg�brica que representa "
//						+ "o total de pontos T em fun��o da ordem n (n = 1, 2, 3, ...), �");
//		setImagem(R.drawable.imagem7, q[9]);
//		adicionarAlternativas(q[9], new Alternativa(4, "T = n^2 + 1"),
//				new Alternativa(1, "T = 2n � 1"), new Alternativa(2,
//						"T = 2n + 1"), new Alternativa(3, "T = n^2 � 1"),
//				new Alternativa(4, "T = n^2 + 1"));
//
//		q[10] = new Questao(
//				"Em uma aula de Matem�tica, o professor apresentou aos alunos uma reta "
//						+ "num�rica como a da figura a seguir. O professor marcou o n�mero 11/4 "
//						+ "nessa reta. Esse n�mero foi marcado entre que pontos da reta num�rica?");
//		setImagem(R.drawable.imagem8, q[10]);
//		adicionarAlternativas(q[10], new Alternativa(3, "2 e 3"),
//				new Alternativa(1, "�4 e �3"), new Alternativa(2, "�3 e �2"),
//				new Alternativa(3, "2 e 3"), new Alternativa(4, "3 e 4"));
//
//		q[11] = new Questao(
//				"Num tabuleiro de xadrez, jogamos com v�rias pe�as que se movimentam de "
//						+ "maneiras diferentes. O cavalo se move para qualquer casa que possa "
//						+ "alcan�ar com movimento na forma de L, de tr�s casas. Na posi��o da "
//						+ "figura, os pontos marcados representam as casas que o cavalo pode "
//						+ "alcan�ar, estando na casa d4. Partindo da casa f5 e fazendo uma �nica "
//						+ "jogada, dentre as casas que o cavalo poder� alcan�ar, est�o");
//		setImagem(R.drawable.imagem9, q[11]);
//		adicionarAlternativas(q[11], new Alternativa(2, "d6 ou g3"),
//				new Alternativa(1, "d3 ou d7"), new Alternativa(2, "d6 ou g3"),
//				new Alternativa(3, "f3 ou h5"), new Alternativa(4, "f7 ou h7"));
//
//		q[12] = new Questao(
//				"A professora desenhou um tri�ngulo no quadro. Em seguida, fez a seguinte "
//						+ "pergunta: �� Se eu ampliar esse tri�ngulo em 3 vezes, como ficar�o as "
//						+ "medidas de seus lados e de seus �ngulos? Alguns alunos responderam: "
//						+ "Fernando: �� Os lados ter�o 3 cm a mais cada um. J� os �ngulos ser�o os "
//						+ "mesmos. Gisele: �� Os lados e �ngulos ter�o suas medidas multiplicadas "
//						+ "por 3. Marina: �� A medida dos lados eu multiplico por 3 e a medida dos "
//						+ "�ngulos eu mantenho as mesmas. Roberto: �� A medida da base ser� a mesma "
//						+ "(5 cm), os outros lados eu multiplico por 3 e mantenho a medida dos �ngulos. "
//						+ "Qual dos alunos acertou a pergunta da professora?");
//		setImagem(R.drawable.imagem10, q[12]);
//		adicionarAlternativas(q[12], new Alternativa(3, "Marina"),
//				new Alternativa(1, "Fernando"), new Alternativa(2, "Gisele"),
//				new Alternativa(3, "Marina"), new Alternativa(4, "Roberto"));
//
//		q[13] = new Questao(
//				"Jo�o e Pedro foram a um restaurante almo�ar e a conta deles foi de R$ 28,00. "
//						+ "A conta de Pedro foi o triplo do valor de seu amigo. O sistema de equa��es "
//						+ "do 1� grau que melhor traduz o problema �");
//		setImagem(R.drawable.imagem11, q[13]);
//		adicionarAlternativas(q[13], new Alternativa(3, "C"), new Alternativa(
//				1, "A"), new Alternativa(2, "B"), new Alternativa(3, "C"),
//				new Alternativa(4, "D"));
//
//		q[14] = new Questao(
//				"Uma caixa d��gua tem suas dimens�es indicadas conforme a figura abaixo. A "
//						+ "quantidade de �gua, em metros c�bicos, que essa caixa pode armazenar �");
//		setImagem(R.drawable.imagem12, q[14]);
//		adicionarAlternativas(q[14], new Alternativa(4, "9,0"),
//				new Alternativa(1, "6,0"), new Alternativa(2, "6,5"),
//				new Alternativa(3, "7,5"), new Alternativa(4, "9,0"));
//
//		q[15] = new Questao("Sendo N = (-3)^2 - 3^2, ent�o, o valor de N �");
//		q[15].setTemImagem(false);
//		adicionarAlternativas(q[15], new Alternativa(2, "0"), new Alternativa(
//				1, "�18"), new Alternativa(2, "0"), new Alternativa(3, "12"),
//				new Alternativa(4, "18"));
//
//		q[16] = new Questao(
//				"Observe os ponteiros nesse rel�gio. Decorridas 3 horas, qual � o menor "
//						+ "�ngulo formado pelos ponteiros?");
//		setImagem(R.drawable.imagem13, q[16]);
//		adicionarAlternativas(q[16], new Alternativa(3, "90�"),
//				new Alternativa(1, "15�"), new Alternativa(2, "45�"),
//				new Alternativa(3, "90�"), new Alternativa(4, "180�"));
//
//		q[17] = new Questao(
//				"Das 15 bolinhas de gude que tinha, Paulo deu 6 para o seu irm�o. "
//						+ "Considerando-se o total de bolinhas, a fra��o que representa o "
//						+ "n�mero de bolinhas que o irm�o de Paulo ganhou �");
//		q[17].setTemImagem(false);
//		adicionarAlternativas(q[17], new Alternativa(1, "6/15"),
//				new Alternativa(1, "6/15"), new Alternativa(2, "9/15"),
//				new Alternativa(3, "15/9"), new Alternativa(4, "15/6"));
//
//		q[18] = new Questao(
//				"Paulo � dono de uma f�brica de m�veis. Para calcular o pre�o V de "
//						+ "venda, em reais, de cada m�vel que fabrica, ele usa a seguinte "
//						+ "f�rmula: V = 1,5 C + 10, sendo C o pre�o de custo em reais desse "
//						+ "m�vel. Considere que o pre�o de custo de um m�vel que Paulo "
//						+ "fabrica � R$ 100,00. Ent�o, ele vende esse m�vel por");
//		q[18].setTemImagem(false);
//		adicionarAlternativas(q[18], new Alternativa(3, "R$ 160,00"),
//				new Alternativa(1, "R$ 110,00"),
//				new Alternativa(2, "R$ 150,00"),
//				new Alternativa(3, "R$ 160,00"),
//				new Alternativa(4, "R$ 210,00"));
//
//		q[19] = new Questao(
//				"Fazendo-se as opera��es indicadas em 0,74 + 0,5 � 1,5 obt�m-se");
//		q[19].setTemImagem(false);
//		adicionarAlternativas(q[19], new Alternativa(2, "-0,26"),
//				new Alternativa(1, "-0,64"), new Alternativa(2, "-0,26"),
//				new Alternativa(3, "0,26"), new Alternativa(4, "0,64"));
//
//		q[20] = new Questao(
//				"A figura representa uma escada apoiada em uma parede perpendicular "
//						+ "ao solo. O topo da escada est� a 7 m de altura, e seu p� est� "
//						+ "afastado da parede 2 m. A escada mede, aproximadamente,");
//		setImagem(R.drawable.imagem14, q[20]);
//		adicionarAlternativas(q[20], new Alternativa(3, "7,3 m"),
//				new Alternativa(1, "5 m"), new Alternativa(2, "6,7 m"),
//				new Alternativa(3, "7,3 m"), new Alternativa(4, "9 m"));
//
//		q[21] = new Questao(
//				"Em uma cidade do Alasca, o term�metro marcou �15 �C pela manh�. "
//						+ "Se a temperatura descer mais 13 �C, o term�metro ir� marcar");
//		q[21].setTemImagem(false);
//		adicionarAlternativas(q[21], new Alternativa(1, "-28 �C"),
//				new Alternativa(1, "-28 �C"), new Alternativa(2, "-2 �C"),
//				new Alternativa(3, "2 �C"), new Alternativa(4, "28 �C"));
//
//		q[22] = new Questao(
//				"Observe este gr�fico, em que est�o representadas duas retas:");
//		setImagem(R.drawable.imagem15, q[22]);
//		adicionarAlternativas(q[22], new Alternativa(4, "8 e �1"),
//				new Alternativa(1, "�1 e 8"), new Alternativa(2, "2 e 3"),
//				new Alternativa(3, "3 e 2"), new Alternativa(4, "8 e �1"));
//
//		q[23] = new Questao(
//				"O custo total C, em milhares de reais, para se produzir x "
//						+ "m�quinas � dado pela express�o C(x) = x^2 � x + 10. Se o "
//						+ "custo total foi de 52 mil reais, ent�o, o n�mero de "
//						+ "m�quinas produzidas foi");
//		q[23].setTemImagem(false);
//		adicionarAlternativas(q[23], new Alternativa(2, "7"), new Alternativa(
//				1, "6"), new Alternativa(2, "7"), new Alternativa(3, "8"),
//				new Alternativa(4, "9"));
//
//		q[24] = new Questao(
//				"As arma��es tipo tesouras correspondem ao sistema de vigas "
//						+ "estruturais que sustentam os telhados das casas e s�o, "
//						+ "geralmente, executadas com barras situadas num plano e "
//						+ "ligadas umas ao outras em forma de tri�ngulos interligados "
//						+ "apoiadas nas extremidades. Fabr�cio percebeu que as tesouras "
//						+ "das vigas do telhado da sua casa formavam um tri�ngulo "
//						+ "ret�ngulo que tinha um �ngulo de 68�. Quanto mede o terceiro "
//						+ "�ngulo?");
//		q[24].setTemImagem(false);
//		adicionarAlternativas(q[24], new Alternativa(1, "22�"),
//				new Alternativa(1, "22�"), new Alternativa(2, "45�"),
//				new Alternativa(3, "56�"), new Alternativa(4, "158�"));
//
//		q[25] = new Questao(
//				"Exatamente no centro de uma mesa redonda de 1 m de raio, foi "
//						+ "colocado um prato de 30 cm de di�metro, com doces e salgados "
//						+ "para uma festa de final de ano. Qual a dist�ncia entre a borda "
//						+ "desse prato e a borda da mesa?");
//		q[25].setTemImagem(false);
//		adicionarAlternativas(q[25], new Alternativa(2, "85 cm"),
//				new Alternativa(1, "115 cm"), new Alternativa(2, "85 cm"),
//				new Alternativa(3, "70 cm"), new Alternativa(4, "20 cm"));
//
//		q[26] = new Questao(
//				"Os alunos de uma turma do 9� Ano fizeram uma estimativa para "
//						+ "200 pessoas com base no estudo seguinte.");
//		setImagem(R.drawable.imagem16, q[26]);
//		adicionarAlternativas(q[26], new Alternativa(2, "B"), new Alternativa(
//				1, "A"), new Alternativa(2, "B"), new Alternativa(3, "C"),
//				new Alternativa(4, "D"));
//
//		q[27] = new Questao(
//				"O administrador de um campo de futebol precisa comprar grama "
//						+ "verde escura e verde clara para cobrir o campo com faixas "
//						+ "de �reas iguais e quantidades tamb�m iguais de cada tipo de "
//						+ "grama. O campo � um ret�ngulo com 100 m de comprimento e 50 m "
//						+ "de largura e, para cada 10 m^2 de grama plantada, � gasto 1 m^2 "
//						+ "a mais por causa da perda. Quantos m^2 de grama verde escura o "
//						+ "administrador dever� comprar para cobrir todo o campo?");
//		q[27].setTemImagem(false);
//		adicionarAlternativas(q[27], new Alternativa(3, "2750"),
//				new Alternativa(1, "2250"), new Alternativa(2, "2500"),
//				new Alternativa(3, "2750"), new Alternativa(4, "5000"));
//
//		q[28] = new Questao(
//				"Uma prefeitura aplicou R$ 850 mil na constru��o de 3 creches e "
//						+ "um parque infantil. O custo de cada creche foi de R$ 250 mil. "
//						+ "A express�o que representa o custo do parque, em mil reais, �");
//		q[28].setTemImagem(false);
//		adicionarAlternativas(q[28], new Alternativa(4, "x + 750 = 850"),
//				new Alternativa(1, "x + 850 = 250"), new Alternativa(2,
//						"x � 850 = 750"), new Alternativa(3, "x + 250 = 850"),
//				new Alternativa(4, "x + 750 = 850"));
//
//		q[29] = new Questao(
//				"Em uma loja de inform�tica, Pedro comprou: um computador no valor "
//						+ "de R$ 2200,00, uma impressora por R$ 800,00 e tr�s cartuchos de "
//						+ "tinta que custam R$ 90,00 cada um. Essas mercadorias foram pagas "
//						+ "em cinco parcelas de mesmo valor. O valor de cada parcela, em "
//						+ "reais, foi igual a");
//		q[29].setTemImagem(false);
//		adicionarAlternativas(q[29], new Alternativa(4, "654"),
//				new Alternativa(1, "414"), new Alternativa(2, "494"),
//				new Alternativa(3, "600"), new Alternativa(4, "654"));
//
//		q[30] = new Questao(
//				"Para ligar a energia el�trica em seu apartamento, Felipe contratou "
//						+ "um eletricista que mediu a dist�ncia do poste da rede el�trica "
//						+ "at� seu im�vel. Essa dist�ncia � representada pela express�o "
//						+ "( 2 raiz de 50 + 6 raiz de 12 )m. Para fazer a liga��o, ser� "
//						+ "necess�rio o dobro da medida fornecida pela express�o, j� que "
//						+ "ser�o necess�rios dois fios. Nessas condi��es, a quantidade "
//						+ "aproximada de fio, em metros, que Felipe ter� que comprar � de"
//						+ "reais, foi igual a");
//		q[30].setTemImagem(false);
//		adicionarAlternativas(q[30], new Alternativa(3, "34,86"),
//				new Alternativa(1, "18,48"), new Alternativa(2, "32,00"),
//				new Alternativa(3, "34,86"), new Alternativa(4, "38,00"));
//
//		q[31] = new Questao(
//				"Quantos quilogramas de sementes s�o necess�rios para semear uma �rea "
//						+ "de 10 m x 24 m, observando a recomenda��o de aplicar 1 kg de "
//						+ "semente por 16 m^2 de terreno?");
//		q[31].setTemImagem(false);
//		adicionarAlternativas(q[31], new Alternativa(4, "15"), new Alternativa(
//				1, "1/5"), new Alternativa(2, "1,5"), new Alternativa(3,
//				"2,125"), new Alternativa(4, "15"));
//
//		q[32] = new Questao(
//				"O projeto original da �rvore de natal da cidade em que Roberto mora "
//						+ "est� indicado a seguir. Se um novo projeto de dimens�es duas vezes "
//						+ "menores que as do projeto original for desenvolvido, as dimens�es "
//						+ "obtidas ser�o");
//		setImagem(R.drawable.imagem17, q[32]);
//		adicionarAlternativas(q[32], new Alternativa(2, "divididas por dois"),
//				new Alternativa(1, "multiplicadas por dois"), new Alternativa(
//						2, "divididas por dois"), new Alternativa(3,
//						"subtra�das em duas unidades"), new Alternativa(4,
//						"divididas por quatro"));
//
//		q[33] = new Questao(
//				"Uma casa tem 3,88 metros de altura. Um engenheiro foi contratado "
//						+ "para projetar um segundo andar e foi informado que a prefeitura "
//						+ "s� permite construir casas de dois andares com altura de at� "
//						+ "7,80 metros. Qual deve ser a altura m�xima, em metros, do "
//						+ "segundo andar?");
//		q[33].setTemImagem(false);
//		adicionarAlternativas(q[33], new Alternativa(1, "3,92"),
//				new Alternativa(1, "3,92"), new Alternativa(2, "4,00"),
//				new Alternativa(3, "4,92"), new Alternativa(4, "11,68"));
//
//		q[34] = new Questao(
//				"Uma torneira com defeito desperdi�a 2,206 litros de �gua por dia. "
//						+ "Isto significa que a torneira desperdi�a 2 litros e");
//		q[34].setTemImagem(false);
//		adicionarAlternativas(q[34], new Alternativa(4,
//				"206 mil�simos de litros"), new Alternativa(1,
//				"0,206 cent�simos de litros"), new Alternativa(2,
//				"0,206 d�cimos de litros"), new Alternativa(3,
//				"206 cent�simos de litros"), new Alternativa(4,
//				"206 mil�simos de litros"));
//
//		q[35] = new Questao(
//				"� comum encontrar em acampamentos barracas de dormir que apresentam "
//						+ "laterais resistentes a chuvas e um fundo feito de pl�stico "
//						+ "resistente, que possuem formato como a figura apresentada a seguir.");
//		setImagem(R.drawable.imagem18, q[35]);
//		adicionarAlternativas(q[35], new Alternativa(3, "C"), new Alternativa(
//				1, "A"), new Alternativa(2, "B"), new Alternativa(3, "C"),
//				new Alternativa(4, "D"));
		
		q[0] = new Questao("O clima de uma regi�o depende de sua localiza��o na terra." +
				" A temperatura tamb�m. Qual das temperaturas abaixo � negativa, ou seja, " +
				"menor que zero?");
		adicionarAlternativas(q[0], new Alternativa(3, "-15�C"), new Alternativa(
				1, "0�C"), new Alternativa(2, "15�C"), new Alternativa(3, "-15�C"),
				new Alternativa(4, "1�C"));
		
		q[1] = new Questao("O que significa dizer que um mergulhador est� a 12 " +
				"metros abaixo do n�vel do mar, utilizando a matem�tica?");
		adicionarAlternativas(q[1], new Alternativa(1, "-12 metros"), new Alternativa(
				1, "-12 metros"), new Alternativa(2, "12 metros"), new Alternativa(3, "-11 metros"),
				new Alternativa(4, "11 metros"));
		
		q[2] = new Questao("O banco em que Ant�nio � cliente avisou que ele " +
				"estava devendo 148,00. Isso significa que no extrato banc�rio apareceu?");
		adicionarAlternativas(q[2], new Alternativa(2, "-148,00"), new Alternativa(
				1, "148,00"), new Alternativa(2, "-148,00"), new Alternativa(3, "-140,00"),
				new Alternativa(4, "-146,00"));
		
		q[3] = new Questao("O ponto mais alto do Brasil � o Pico da Neblina, que tem 2.994 metros " +
				"de altitude. Indique essa altitude usando um n�mero inteiro positivo ou negativo.");
		adicionarAlternativas(q[3], new Alternativa(4, "+2.994"), new Alternativa(
				1, "-2.994"), new Alternativa(2, "+2.996"), new Alternativa(3, "-2.995"),
				new Alternativa(4, "+2.994"));
		
		q[4] = new Questao("-18�C � uma temperatura abaixo de 0�C. Encontre agora uma " +
				"temperatura acima de 0�C.");
		adicionarAlternativas(q[4], new Alternativa(4, "+20�C"), new Alternativa(
				1, "-20�C"), new Alternativa(2, "-2�C"), new Alternativa(3, "-1�C"),
				new Alternativa(4, "+20�C"));
		
		q[5] = new Questao("O que significa dever R$100,00?");
		adicionarAlternativas(q[5], new Alternativa(3, "-100,00 R$"), new Alternativa(
				1, "-150,00 R$"), new Alternativa(2, "+100,00 R$"), new Alternativa(3, "-100,00 R$"),
				new Alternativa(4, "-110,00 R$"));
		
		q[6] = new Questao("O que corresponde a 11 andares acima do andar t�rreo?");
		adicionarAlternativas(q[6], new Alternativa(1, "+11"), new Alternativa(
				1, "+11"), new Alternativa(2, "+12"), new Alternativa(3, "+10"),
				new Alternativa(4, "-11"));
		
		q[7] = new Questao("Pedro n�o tem nenhum valor em sua conta banc�ria " +
				"significa que seu saldo est�?");
		adicionarAlternativas(q[7], new Alternativa(2, "0"), new Alternativa(
				1, "2"), new Alternativa(2, "0"), new Alternativa(3, "1"),
				new Alternativa(4, "-1"));
		
		q[8] = new Questao("Em uma reta num�rica verifique qual dos n�meros " +
				"est� mais distante da origem?");
		adicionarAlternativas(q[8], new Alternativa(2, "10"), new Alternativa(
				1, "4"), new Alternativa(2, "10"), new Alternativa(3, "5"),
				new Alternativa(4, "9"));
		
		q[9] = new Questao("Quem est� mais pr�ximo do zero em uma reta num�rica?");
		adicionarAlternativas(q[9], new Alternativa(3, "-4"), new Alternativa(
				1, "-8"), new Alternativa(2, "-7"), new Alternativa(3, "-4"),
				new Alternativa(4, "-5"));
		
		q[10] = new Questao("Em uma reta num�rica, qual a dist�ncia do n�mero -6 " +
				"em rela��o ao zero?");
		adicionarAlternativas(q[10], new Alternativa(4, "6"), new Alternativa(
				1, "-5"), new Alternativa(2, "-6"), new Alternativa(3, "5"),
				new Alternativa(4, "6"));
		
		q[11] = new Questao("Observe a listagem de n�meros inteiros: " +
				"-1,-3,-5,-7,-9. Que n�mero seria o pr�ximo?");
		adicionarAlternativas(q[11], new Alternativa(1, "-11"), new Alternativa(
				1, "-10"), new Alternativa(2, "-8"), new Alternativa(3, "-12"),
				new Alternativa(4, "-11"));
		
		q[12] = new Questao("Agora observe: -1,+1,-2,+2,-3. Qual o pr�ximo?");
		adicionarAlternativas(q[12], new Alternativa(3, "+3"), new Alternativa(
				1, "-3"), new Alternativa(2, "-4"), new Alternativa(3, "+3"),
				new Alternativa(4, "+4"));
		
		q[13] = new Questao("Utilizando a reta num�rica verifique qual o " +
				"n�mero sim�trico ou oposto de 4?");
		adicionarAlternativas(q[13], new Alternativa(2, "-4"), new Alternativa(
				1, "-3"), new Alternativa(2, "-4"), new Alternativa(3, "+8"),
				new Alternativa(4, "-8"));
		
		q[14] = new Questao("Qual o sim�trico ou oposto de -20?");
		adicionarAlternativas(q[14], new Alternativa(1, "20"), new Alternativa(
				1, "20"), new Alternativa(2, "0"), new Alternativa(3, "-19"),
				new Alternativa(4, "-21"));
		
		q[15] = new Questao("Qual o par de n�meros sim�tricos.");
		adicionarAlternativas(q[15], new Alternativa(4, "(8,-8)"), new Alternativa(
				1, "(8,9)"), new Alternativa(2, "(4,8)"), new Alternativa(3, "(8,8)"),
				new Alternativa(4, "(8,-8)"));
		
		q[16] = new Questao("Ainda utilizando a reta num�rica, verifique qual � o antecessor de -4?");
		adicionarAlternativas(q[16], new Alternativa(1, "-5"), new Alternativa(
				1, "-5"), new Alternativa(2, "-3"), new Alternativa(3, "-2"),
				new Alternativa(4, "3"));
		
		q[17] = new Questao("Qual o sucessor de -2?");
		adicionarAlternativas(q[17], new Alternativa(3, "-1"), new Alternativa(
				1, "-3"), new Alternativa(2, "-4"), new Alternativa(3, "-1"),
				new Alternativa(4, "3"));
		
		q[18] = new Questao("Qual n�mero inteiro est� entre -3 e -1?");
		adicionarAlternativas(q[18], new Alternativa(2, "-2"), new Alternativa(
				1, "-3"), new Alternativa(2, "-2"), new Alternativa(3, "-1"),
				new Alternativa(4, "2"));
		
		q[19] = new Questao("Indique um n�mero maior que -9?");
		adicionarAlternativas(q[19], new Alternativa(2, "-5"), new Alternativa(
				1, "-6"), new Alternativa(2, "-5"), new Alternativa(3, "-7"),
				new Alternativa(4, "-8"));
		
		q[20] = new Questao("O n�mero 17 � menor que:");
		adicionarAlternativas(q[20], new Alternativa(4, "28"), new Alternativa(
				1, "-6"), new Alternativa(2, "15"), new Alternativa(3, "7"),
				new Alternativa(4, "28"));
		
		q[21] = new Questao("O n�mero -17 � menor que:");
		adicionarAlternativas(q[21], new Alternativa(1, "-8"), new Alternativa(
				1, "-8"), new Alternativa(2, "-18"), new Alternativa(3, "-19"),
				new Alternativa(4, "-28"));
		
		q[22] = new Questao("Os resultados de 9+5 e +5-9 s�o, respectivamente iguais a:");
		adicionarAlternativas(q[22], new Alternativa(4, "14 e -4"), new Alternativa(
				1, "14 e -14"), new Alternativa(2, "13 e -4"), new Alternativa(3, "14 e 4"),
				new Alternativa(4, "14 e -4"));
		
		q[23] = new Questao("Num jogo de ganhar ou perder, Juca fez 3 pontos na 1� rodada e " +
				"7 na segunda. Paulo fez 5 na primeira e 4 na segunda. Quem ganhou o jogo?");
		adicionarAlternativas(q[23], new Alternativa(2, "Juca"), new Alternativa(
				1, "Paulo"), new Alternativa(2, "Juca"));
		
		q[24] = new Questao("Resolva: -3+7,-5+1  e  4- 9.");
		adicionarAlternativas(q[24], new Alternativa(1, "4, -4 e -5"), new Alternativa(
				1, "4, -4 e -5"), new Alternativa(2, "4, -4 e -4"), new Alternativa(3, "-4, -4 e -5"),
				new Alternativa(4, "4, 4 e -5"));
		
		q[25] = new Questao("Qual � o resultado de: 20-23+5-2+4+7?");
		adicionarAlternativas(q[25], new Alternativa(4, "11"), new Alternativa(
				1, "-11"), new Alternativa(2, "10"), new Alternativa(3, "12"),
				new Alternativa(4, "11"));
		
		q[26] = new Questao("O resultado de 10-3+20-30 �?");
		adicionarAlternativas(q[26], new Alternativa(2, "-3"), new Alternativa(
				1, "-1"), new Alternativa(2, "-3"), new Alternativa(3, "3"),
				new Alternativa(4, "1"));
		
		q[27] = new Questao("Laura tinha R$200,00 em sua conta banc�ria, na segunda-feira. Ela " +
				"fez um saque de R$125,00 e depositou um cheque de R$100,00. Qual a express�o " +
				"que melhor representa o que aconteceu com a conta dela?");
		adicionarAlternativas(q[27], new Alternativa(3, "200-125+100"), new Alternativa(
				1, "200+125+100"), new Alternativa(2, "200-125-100"), new Alternativa(3, "200-125+100"),
				new Alternativa(4, "200+125-100"));
		
		q[28] = new Questao("Um certo pr�dio tem v�rios andares inclusive o subsolo. O elevador" +
				" apresenta bot�es de -3 a 10 para cada andar. O t�rreo � considerado o andar " +
				"zero. Um elevador que estava no 5� andar desceu 7 andares. Em que andar ele " +
				"se encontra?");
		adicionarAlternativas(q[28], new Alternativa(1, "-2"), new Alternativa(
				1, "-2"), new Alternativa(2, "-1"), new Alternativa(3, "1"),
				new Alternativa(4, "2"));
		
		q[29] = new Questao("Um elevador apresenta bot�es de -3 a 10 para cada andar. O " +
				"t�rreo � considerado o andar zero.Ele  estava no 3� andar, subiu 2 andares " +
				"e desceu 5. Em que andar ele est�?");
		adicionarAlternativas(q[29], new Alternativa(3, "t�rreo"), new Alternativa(
				1, "1� andar"), new Alternativa(2, "2� andar"), new Alternativa(3, "t�rreo"),
				new Alternativa(4, "3� andar"));
		
		q[30] = new Questao("Um elevador apresenta bot�es de -3 a 10 para cada andar. O " +
				"t�rreo � considerado o andar zero. Ele estava no t�rreo, desceu dois " +
				"andares, parou e, em seguida, desceu outro andar. Em que andar ele se " +
				"encontra?");
		adicionarAlternativas(q[30], new Alternativa(1, "-3"), new Alternativa(
				1, "-3"), new Alternativa(2, "-2"), new Alternativa(3, "3"),
				new Alternativa(4, "2"));
		
		q[31] = new Questao("Quem guarda as economias de Jo�o � o av� dele. Jo�o iniciou" +
				" sua conta com R$20,00, ap�s uns dias deu mais R$24,00 ao av�. Depois " +
				"retirou R$38,00 e passado mais um tempo ele perguntou ao av� se podia " +
				"retirar mais R$5,00. A conta de Jo�o agora est� com saldo positivo ou " +
				"gastou todo o dinheiro? Qual o valor?");
		adicionarAlternativas(q[31], new Alternativa(2, "R$1,00 saldo positivo"), new Alternativa(
				1, "R$2,00 saldo positivo"), new Alternativa(2, "R$1,00 saldo positivo"), new Alternativa(3, "R$-1,00 saldo negativo"),
				new Alternativa(4, "R$-2,00 saldo negativo"));


		questoes = Arrays.asList(q);
		return questoes;
	}

	private static void adicionarAlternativas(Questao questao,
			Alternativa... alternativas) {
		questao.setAlternativaCorreta(alternativas[0]);
		for (int i = 1; i < alternativas.length; i++)
			questao.adicionarAlternativa(alternativas[i]);
	}

	private static void setImagem(int imagem, Questao questao) {
		Bitmap bm = null;
		ByteArrayOutputStream stream;
		bm = BitmapFactory.decodeResource(activityAtual.getResources(), imagem);
		stream = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
		questao.setImagem(stream.toByteArray());
		questao.setTemImagem(true);
	}

}
