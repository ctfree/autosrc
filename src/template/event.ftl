

<#list types as type>
  public static class ${type}Event extends Events {
        public ${type}Event() {
            eventType = Data.EventType.EV_${type};
        }

        public String name;
        
        @Override
        public void readFields(DataInput input) throws IOException {
            name = input.readUTF();
        }

        @Override
        public void write(DataOutput output) throws IOException {
            output.writeUTF(name);
        }

        @Override
        public String toString() {
            return "${type}Event [name=" + name + "]";
        }
    }
</#list>