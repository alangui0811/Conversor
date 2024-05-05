public class Conversion {
    private String target;
    private String origin;
    private double result;

    public void setTarget(String target) {
        this.target = target;
    }
    public void setC(String origin) {
        this.origin = origin;
    }
    public void setResult(double result) {
        this.result = result;
    }
    public String getTarget() {
        return target;
    }
    public String getOrigin() {
        return origin;
    }
    public double getResult() {
        return result;
    }

    public Conversion(Conversion1 conversion1){
        this.target = conversion1.target_code();
        this.origin = conversion1.base_code();
        this.result = conversion1.conversion_result();
    }

    @Override
    public String toString(){
        return "Conversion a " + this.getTarget() + ": " + this.getResult();
    }

}
