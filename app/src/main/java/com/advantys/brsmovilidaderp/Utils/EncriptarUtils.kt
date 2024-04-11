package com.advantys.brsmovilidaderp.Utils

import kotlin.experimental.xor

class EncriptarUtils {

    companion object {
        private lateinit var CRC_16: ShortArray
        private lateinit var CRC_32: IntArray

        init {
            precalc16()
            precalc32()
        }

        private fun crc16(data: ByteArray): Short {
            return crc16(data, 0.toShort())
        }

        private fun crc16(data: ByteArray, crc: Short): Short {
            var crcLocal = crc
            for (i in data.indices) {
                crcLocal =
                    (CRC_16[((crcLocal.toInt() ushr 8) xor data[i].toInt()) and 0xff] xor ((crcLocal.toInt() shl 8).toShort())).toShort()
            }
            return crcLocal
        }

        private fun crc32(data: ByteArray): Int {
            return crc32(data, 0)
        }

        private fun crc32(data: ByteArray, crc: Int): Int {
            var crcLocal = crc.inv()
            for (i in data.indices) {
                crcLocal =
                    CRC_32[(crcLocal xor data[i].toInt()) and 0xff] xor ((crcLocal ushr 8) and 0xffffff)
            }
            return crcLocal.inv()
        }

        fun crc32(data: Byte, length: Int, crc: Int): Int {
            var crcLocal = crc.inv()
            repeat(length) {
                crcLocal =
                    CRC_32[(crcLocal xor data.toInt()) and 0xff] xor ((crcLocal ushr 8) and 0xffffff)
            }
            return crcLocal.inv()
        }

        private fun precalc16() {
            CRC_16 = ShortArray(256)
            for (i in 0 until 256) {
                var crc: Short = (i shl 8).toShort()
                for (k in 0 until 8) {
                    crc =
                        ((crc.toInt() shl 1) xor if ((crc.toInt() and 0x8000) != 0) 0x1021 else 0).toShort()
                }
                CRC_16[i] = (crc.toInt() and 0xffff).toShort()
            }
        }

        private fun precalc32() {
            CRC_32 = IntArray(256)
            for (i in 0 until 256) {
                var crc = i
                for (k in 0 until 8) {
                    crc = (crc ushr 1) xor if ((crc and 0x00000001) != 0) 0xedb88320L.toInt() else 0
                }
                CRC_32[i] = crc and 0xffffffff.toInt()
            }
        }

        fun CRC16(base: String): String {
            val bytesSource = base.toByteArray()
            return Integer.toHexString(crc16(bytesSource).toInt())
        }

        fun CRC32(base: String): String {
            val bytesSource = base.toByteArray()
            var resultado = Integer.toHexString(crc32(bytesSource))
            while (resultado.length < 8) {
                resultado = "0$resultado"
            }
            return resultado
        }

        fun CRCCCITT(base: String): String {
            var crc = 0xFFFF // initial value
            val polynomial = 0x1021 // 0001 0000 0010 0001  (0, 5, 12)
            val bytes = base.toByteArray()
            for (b in bytes) {
                for (i in 0 until 8) {
                    val bit = (b.toInt() ushr (7 - i) and 1) == 1
                    val c15 = (crc ushr 15 and 1) == 1
                    crc = crc shl 1
                    if (c15 xor bit) crc = crc xor polynomial
                }
            }
            crc = crc and 0xffff
            var resultado = Integer.toHexString(crc)
            resultado = when (resultado.length) {
                0 -> "0000"
                1 -> "000$resultado"
                2 -> "00$resultado"
                3 -> "0$resultado"
                else -> resultado
            }
            return resultado
        }
    }
}