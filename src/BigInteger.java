import java.util.ArrayList;
import java.util.Objects;

public class BigInteger {

    private ArrayList<Integer> bits = new ArrayList<>();
    private int sign = 0; // -1 for negative, 0 for 0, 1 for positive

    BigInteger(int value) {
        if (value > 0) {
            sign = 1;
            bits.add(value);
        } else if (value < 0) {
            sign = -1;
            bits.add(-value);
        } else {
            bits.add(value);
        }
    }

    private BigInteger(ArrayList<Integer> bits, int sign) {
        this.bits = new ArrayList<>(bits);
        this.sign = sign;
    }

    private BigInteger(BigInteger other) {
        bits = new ArrayList<>(other.bits);
        sign = other.sign;
    }

    public BigInteger opposite() {
        return new BigInteger(bits, -sign);
    }

    private void padArrays(ArrayList<Integer> array1, ArrayList<Integer> array2)
    {
        // Pad the smaller number
        int bitAmountDifference = array1.size() - array2.size();
        for (int i = 0; i < Math.abs(bitAmountDifference); i++) {
            if (bitAmountDifference > 0) {
                array2.add(0);
            }
            else {
                array1.add(0);
            }
        }
    }

    public BigInteger add(BigInteger other) {
        if (sign == 0) {
            return new BigInteger(other);
        } else if (other.sign == 0) {
            return new BigInteger(this);
        }

        boolean areSameSign = sign < 0 && other.sign < 0 || sign > 0 && other.sign > 0;
        if (!areSameSign) {
            if (sign > 0) {
                return this.subtractSameSign(other.opposite());
            } else {
                return other.subtractSameSign(opposite());
            }
        }

        return addSameSign(other);
    }

    private BigInteger addSameSign(BigInteger other) {
        ArrayList<Integer> newBits = new ArrayList<>();

        ArrayList<Integer> thisBits = new ArrayList<>(bits);
        ArrayList<Integer> otherBits = new ArrayList<>(other.bits);

        padArrays(thisBits, otherBits);

        int bitsAmount = thisBits.size();
        int carryOver = 0;
        for (int i = 0; i < bitsAmount; i++) {
            int valueThis = thisBits.get(i);
            int valueOther = otherBits.get(i);
            int newvalue = 0;
            for (int j = 0; j < 32; j++) {
                int mask = 1 << j;
                int shifted1 = ((valueThis & mask) >>> j);
                int shifted2 = ((valueOther & mask) >>> j);

                int sum = (shifted1 + shifted2) + carryOver;
                int bitSumValue = sum & 1;
                carryOver = sum > 1 ? 1 : 0;
                newvalue |= bitSumValue << j;
            }
            newBits.add(newvalue);
        }

        if (carryOver > 0) {
            newBits.add(1);
        }

        return new BigInteger(newBits, sign);
    }

    public BigInteger subtract(BigInteger other) {
        if (sign == 0) {
            return other.opposite();
        } else if (other.sign == 0) {
            return new BigInteger(this);
        }

        boolean areSameSign = sign < 0 && other.sign < 0 || sign > 0 && other.sign > 0;
        if (!areSameSign) {
            return addSameSign(other.opposite());
        }

        return subtractSameSign(other);
    }

    private BigInteger subtractSameSign(BigInteger other) {
        ArrayList<Integer> newBits = new ArrayList<>();

        ArrayList<Integer> largeBits;
        ArrayList<Integer> smallBits;

        int newSign = sign;
        boolean thisIsLarge = sign > 0 && LargerThan(other) || sign < 0 && other.LargerThan(this);

        if (thisIsLarge) {
            largeBits = new ArrayList<>(bits);
            smallBits = new ArrayList<>(other.bits);
        }
        else {
            largeBits = new ArrayList<>(other.bits);
            smallBits = new ArrayList<>(bits);
            newSign = -sign;
        }

        padArrays(largeBits, smallBits);

        int bitsAmount = largeBits.size();
        int carryOver = 0;
        for (int i = 0; i < bitsAmount; i++) {
            int valueLarge = largeBits.get(i);
            int valueSmall = smallBits.get(i);
            int newvalue = 0;
            for (int j = 0; j < 32; j++) {
                int mask = 1 << j;
                int subtraction = (((valueLarge & mask) - (valueSmall & mask)) >> j) - carryOver;
                int bitSumValue = (subtraction == 1 || subtraction == -1) ? 1 : 0;
                carryOver = subtraction < 0 ? 1 : 0;
                newvalue += bitSumValue << j;
            }
            newBits.add(newvalue);
        }

        // Remove any trailing zeroes
        for (int i =  bitsAmount - 1; i > 1; i--) {
            if (newBits.get(i) == 0) {
                newBits.remove(i);
            }
        }

        return new BigInteger(newBits, newSign);
    }

    public boolean LargerThan(BigInteger other) {
        if (sign != other.sign) {
            return sign > other.sign;
        }

        if (bits.size() != other.bits.size()) {
            return bits.size() > other.bits.size();
        }

        for (int i = bits.size() - 1; i >= 0; i--){
            int valueThis = bits.get(i);
            int valueOther = other.bits.get(i);
            for (int j = 31; j >=0; j--) {
                int valueThisShifted = valueThis >> j;
                int valueOtherShifted = valueOther >> j;
                if (valueThisShifted != valueOtherShifted) {
                    if (sign >= 0) {
                        return valueThisShifted > valueOtherShifted;
                    }
                    else {
                        return valueThisShifted < valueOtherShifted;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != getClass()) {
            return false;
        }

        final BigInteger other = (BigInteger) obj;

        if (sign != other.sign)
        {
            return false;
        }

        if (bits.size() != other.bits.size())
        {
            return false;
        }

        for (int i = 0; i < bits.size(); i++){
            if (!Objects.equals(bits.get(i), other.bits.get(i))) {
                return false;
            }
        }

        return true;
    }
}
