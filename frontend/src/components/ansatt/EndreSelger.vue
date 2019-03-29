<template>
  <div id="opprett-selger">
    <input v-model="selger.navn" placeholder="Selgernavn">
    <button v-on:click="endre">Opprett/ endre selger</button>
    <p>{{ melding }}</p>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        selger: {
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
        var selgerId = ''
        if (this.$route.params.id != undefined) {
          selgerId = this.$route.params.id
        }

        axios
          .put('/ansatt/' + selgerId, {
              navn: this.selger.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => (

            this.melding = 'Selger er oppdatert!'
          ))
          .catch(error => {
            this.melding ="Feil. Sjekk console"
            console.log(error.response)
          });

        this.melding = 'Selger ' + this.selgernavn + ' ble opprettet'
        this.selger.navn = ''
        this.selger.id = ''
        console.log(this.melding)
//        this.$router.push('selger')
      }
    },
    watch: {

      '$route.params.id'(newId, oldId) {
        this.melding =''
        if (this.$route.params.id == undefined) {
          return
        }
        var selgerId = this.$route.params.id

        axios.get('/ansatt/' + selgerId, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selger = response.data))

      }

    }
  }
</script>