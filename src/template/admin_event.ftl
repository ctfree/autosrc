<#list types as type>
        new ${type?replace("_","")}Event().regist();
</#list>

<#list types as type>
  public static class ${type?replace("_","")}Event extends AdminEvents {
        public ${type?replace("_","")}Event() {
            eventType = Data.AdminEventType.EV_${type};
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
            return "${type?replace("_","")}Event [name=" + name + "]";
        }
    }
</#list>