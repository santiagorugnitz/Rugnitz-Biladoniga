package backend;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class Envase {

    private String nombre;
    private int id;
    private int vecesUsado;
    private Articulo.Tipo[] tipos;
    private int costeProduccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getVecesUsado() {
        return vecesUsado;
    }

    private void setVecesUsado(int vecesUsado) {
        this.vecesUsado = vecesUsado;
    }

    public Articulo.Tipo[] getTipos() {
        return tipos;
    }

    public void setTipos(Articulo.Tipo[] tipos) {
        this.tipos = tipos;
    }

    public int getCosteProduccion() {
        return costeProduccion;
    }

    public void setCosteProduccion(int costeProduccion) {
        this.costeProduccion = costeProduccion;
    }

    public Envase() {
        this.setNombre("");
        this.setId(0);
        this.setTipos(new Articulo.Tipo[0]);
        this.setCosteProduccion(0);
        this.setVecesUsado(0);
    }

    public Envase(String nombre, int id, Articulo.Tipo[] tipos, int costeProduccion) {
        this.setNombre(nombre);
        this.setId(id);
        this.setVecesUsado(0);
        this.setTipos(tipos);
        this.setCosteProduccion(costeProduccion);
    }

    public void aumentarUso(int n) {
        this.setVecesUsado(this.getVecesUsado() + n);
    }

    /**
     * Busca si el tipo recibido pertenece al array de tipos
     *
     * @param t tipo de articulo que se quiere comprobar
     * @return true si el envase admite el tipo, de lo contrario false
     */
    public boolean admiteElTipo(Articulo.Tipo t) {
        for (Articulo.Tipo tipo : tipos) {
            if (t == tipo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Envase other = (Envase) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

}
