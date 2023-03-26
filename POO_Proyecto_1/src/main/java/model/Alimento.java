package model;


    public class Alimento {
        protected int cordenadad[];
        protected int cantidad;
        protected int turnosParaReaparecer;
    
        public Alimento() {
            this.cordenadad[0] = 0; 
            this.cordenadad[1] = 0;
            this.cantidad = 1;
            this.turnosParaReaparecer = 0;
        
        }

        public void serComido (Organismo org) { 
        }

        public void setPosicion(int x, int y) {
        }


        public void setCordenadas(int[] is) {
        }

        public void setCoordenadas(int x, int y) {
        }
        
    }
    

