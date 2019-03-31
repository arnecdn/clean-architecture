<template>
  <div>
    <input v-model="kundeTilLagring.navn" placeholder="kundenavn">
    <button v-on:click="lagrekunde" v-text="knappTekst"></button>
    <p>{{ melding }}</p>
    <li v-for="kunde in kunder" >
      <router-link :to="{name:'kunde_lagre', params:{ id: kunde.id }}">{{kunde.navn}}</router-link>
    </li>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        knappTekst: 'Opprett kunde',
        kunder: [],
        kundeTilLagring: {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      hentAlle() {
        axios.get('/api/kunde', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.kunder = response.data))

      },
      lagrekunde() {
        if (this.kundeTilLagring.navn.length == 0) {
          return;
        }
        var kundeId = ''
        if (this.$route.params.id != undefined) {
          kundeId = this.$route.params.id
        }

        axios
          .put('/api/kunde/' + kundeId, {
              navn: this.kundeTilLagring.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => (
            this.melding = 'kunde er oppdatert!'
          ))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

        this.melding = 'kunde ' + this.kundenavn + ' ble opprettet'
        this.kundeTilLagring.navn = ''
        this.kundeTilLagring.id = ''
        this.hentAlle()
        this.$router.push('/kunde')
      }
    },
    mounted() {
      console.log('hent alle')
      this.hentAlle()
    },
    watch: {
      '$route.params.id'(newId, oldId) {
        this.melding = ''
        if (this.$route.params.id == undefined) {
          'Opprett kunde'
          return
        }

        this.knappTekst = 'Lagre kunde'

        var kundeId = this.$route.params.id

        axios.get('/api/kunde/' + kundeId, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.kundeTilLagring = response.data))

      },
      '$route' (to, from) {
        this.hentAlle()
      }
    }
  }
</script>