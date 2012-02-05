package br.dcc.ufrj.asrankvrp.simulator;

import br.dcc.ufrj.antvrp.simulator.Simulator;
import br.dcc.ufrj.antvrp.world.World;
import br.dcc.ufrj.asrankvrp.world.ASrankWorld;

public class AsRankSimulator extends Simulator{
	
	private void printHeader(){
		System.out.println("============================================================================");
		System.out.println("Universidade Federeal do Rio de Janeiro");
		System.out.println("Instituto de Matem�tica - Departamento de Ci�ncia da Computa��o");
		System.out.println("Projeto Final de Curso");
		System.out.println("Aluno: Fabio Augusto Antunes Barbosa DRE:102025586");
		System.out.println("Orientador: Jo�o Carlos Pereira da Silva");		
		System.out.println("Implementa��o do Algoritmo ASrank para o Problema de Roteamento de Ve�culos.");
		System.out.println("============================================================================\n\n");
	}
	
	private void printResults(World world, long tempo){
		System.out.println("\n*******************************************************************************************");
		System.out.println("Melhor Tour: " + world.getBestTour());
		System.out.println("Numero de Ve�culos: " + world.getBestTour().getRoutes().size());
		System.out.println("Tamanho Melhor Tour: " + world.getBestTour().getDistance());
		System.out.println("Tempo total da simula��o: " + tempo + " milissegundos\n");
		System.out.println(world.getBestTour().getDistance());
	}
	
	private void printInputs(World world)  throws Exception{
		System.out.println("Iniciando execu��o do aplicativo... ");
		System.out.println("Quantidade de simula��es: " + this.getNumeroSimulacoes());
		System.out.println("Quantidade de rodadas est�veis: " + this.getRodadasEstaveis());
		System.out.println("Quantidade de formigas: " + this.getAntAmount());
		System.out.println("Rank size: " + this.getRankSize());
		System.out.println("Seed: " + world.getSeed() + "\n");
	}
	
	private void printIntance(ASrankWorld world) {
		System.out.println("Nome: " + world.getName());
		System.out.println("Coment�rios: " + world.getComment());
		System.out.println("Tipo: " + world.getType());
		System.out.println("Dimens�o: " + world.getDimension());
		System.out.println("Edge Weight Type: " + world.getEdgeWeightType());
		System.out.println("Capacidade: " + world.getCapacity() + "\n");
		
	}	

	public static void main(String[] args) throws Exception {
		AsRankSimulator simulator = new AsRankSimulator();
		simulator.printHeader();
		
		//simulator.setFileName();
		if (simulator.isDefaultMode()){
			simulator.setNumeroSimulacoes();
			simulator.setQuantidadeFormigas();
			simulator.setTamanhoRank();
		}
		
		ASrankWorld world = new ASrankWorld();
		world.createWorld(simulator.getFileName());
		
		simulator.printInputs(world);		
		simulator.printIntance(world);
		
		world.createAnts(simulator.getAntAmount());
		world.setRankSize(simulator.getRankSize());
		world.createPheromones();
		
		for(int s = 0; s < simulator.getNumeroSimulacoes(); s++){
			long t2 = System.currentTimeMillis();
			double bestSolution = 0;
			
			for (int i = 1, j = 0; j <= simulator.getRodadasEstaveis(); i++, j++) {
				world.run();
				if (world.getBestTour().getDistance() < bestSolution || bestSolution == 0) {
					bestSolution = world.getBestTour().getDistance();
					j = 0;
				}
			}
			
			simulator.printResults(world, System.currentTimeMillis() - t2);
			world.reset();
		}
	}
}
