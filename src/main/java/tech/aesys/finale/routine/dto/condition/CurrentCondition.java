package tech.aesys.finale.routine.dto.condition;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CurrentCondition {

        private String text;

        private Integer icon;

        private Long code;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CurrentCondition currentCondition = (CurrentCondition) o;
            return Objects.equals(this.text, currentCondition.text) &&
                    Objects.equals(this.icon, currentCondition.icon) &&
                    Objects.equals(this.code, currentCondition.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text, icon, code);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class CurrentCondition {\n");
            sb.append("    text: ").append(toIndentedString(text)).append("\n");
            sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
            sb.append("    code: ").append(toIndentedString(code)).append("\n");
            sb.append("}");
            return sb.toString();
        }

        /**
         * Convert the given object to string with each line indented by 4 spaces
         * (except the first line).
         */
        private String toIndentedString(Object o) {
            if (o == null) {
                return "null";
            }
            return o.toString().replace("\n", "\n    ");
        }
    }

