package br.com.caelum.notasfiscais.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class CicloDeVidaListener implements PhaseListener {
	
	public void afterPhase(PhaseEvent event){
		System.out.println("Depois da fase:" + event.getPhaseId());		
	}
	
	public void beforePhase(PhaseEvent event){
		System.out.println("Antes da fase: " + event.getPhaseId());		
	}
	
	public PhaseId getPhaseId(){
		return PhaseId.ANY_PHASE;
	}

}
