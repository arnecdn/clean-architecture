<template>
  <div id="opprett-kunde">
    <input v-model="selgernavn" placeholder="Selgernavn">
    <button v-on:click="opprett">Opprett selger</button>
    <p>{{ melding }}</p>
  </div>
</template>

<script>
  export default {

    data () {
      return {
        selgernavn: "",
        melding: ""
      }
    },
    methods: {
      opprett () {
        if (this.selgernavn.length == 0) {
          return;
        }

        this.melding = 'Selger ' + this.selgernavn + ' ble opprettet'
        console.log(this.melding)
      }
    }
  }
</script>