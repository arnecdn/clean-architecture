<template>
  <div id="opprett-selger">
    <input v-model="selger.navn" placeholder="Selgernavn">
    <button v-on:click="endre">Endre selger </button>
    <p>{{ melding }}</p>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        selgernavn: "",
        melding: "",
        selger : {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      endre() {
        if (this.selger.navn.length == 0) {
          return;
        }
        axios
          .put('/ansatt/' + this.$route.params.id, {
              navn: this.selger.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => (this.melding = response))

        this.melding = 'Selger ' + this.selgernavn + ' ble opprettet'

        console.log(this.melding)
      }
    },
  mounted() {
    axios.get('/ansatt/' + this.$route.params.id, {},
      {
        headers: {
          'Content-type': 'application/json',
        }
      }
    ).then(response => (this.selger = response.data))
  }
  }
</script>