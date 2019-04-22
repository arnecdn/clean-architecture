<template>
  <div>
    <table>
      <tr>
        <td>
          Kunde:
          <select v-model="salgTilLagring.kunde">
            <option v-for="k in kunder" :value="k">{{k.navn}}</option>
          </select>
        </td>
        <td>
          Selger:
          <select v-model="salgTilLagring.selger">
            <option v-for="s in selgere" :value="s">{{s.navn}}</option>
          </select>
        </td>
        <td>
          Produkt:
          <select v-model="salgTilLagring.produkt">
            <option v-for="p in produkter" :value="p">{{ p.beskrivelse }}</option>
          </select>
        </td>
        <td>
          Antall:
          <select v-model="salgTilLagring.antall">
            <option v-for="i in 20" :value="i">{{ i }}</option>
          </select>
        </td>
        <td>
          <button v-if="salgTilLagring.id == ''" v-on:click.stop.prevent="opprettSalg">Opprett salg</button>
          <button v-if="salgTilLagring.id != ''" v-on:click.stop.prevent="lagreSalg(salgTilLagring.id)">Lagre salg</button>
        </td>

      </tr>
      <tr>
        <td>
          <input v-model="salgTilLagring.kunde.navn" placeholder="kunde" :disabled="true">
        </td>
        <td>
          <input v-model="salgTilLagring.selger.navn" placeholder="selger" :disabled="true">
        </td>
        <td>
          <input v-model="salgTilLagring.produkt.beskrivelse" placeholder="Produkt" :disabled="true">
        </td>
        <td>
          <input v-model="salgTilLagring.antall" placeholder="antall" :disabled="true">
        </td>
        <td>
        </td>
      </tr>
    </table>
    <p>{{ melding }}</p>
    <table>
      <tr>
        <td>Administrer</td>
        <td>Produkt</td>
        <td>Antall</td>
        <td>Totalpris</td>
        <td>Selger</td>
        <td>Kunde</td>
        <td>Pris</td>
      </tr>
      <tr v-for="salg in salgsListe">
        <td>
          <button v-on:click="slettSalg(salg.id)">Slett</button>
          <button v-on:click="hentSalg(salg.id)">Endre</button>
        </td>
        <td>{{salg.produkt.beskrivelse}}</td>
        <td>{{salg.antall}}</td>
        <td>{{salg.totalPris}}</td>
        <td>{{salg.selger.navn}}</td>
        <td>{{salg.kunde.navn}}</td>
      </tr>
    </table>
  </div>
</template>

<script>

  import gpl from 'graphql-tag'


  export default {

    data() {
      return {
        selgere: [],
        kunder: [],
        produkter: [],
        salgsListe: [],
        melding: "",
        salgTilLagring: {
          id: '',
          produkt: '',
          antall: '',
          selger: '',
          kunde: ''
        }
      }
    },
    methods: {

      tømFelter() {
        this.salgTilLagring.id = ''
        this.salgTilLagring.antall = '',
          this.salgTilLagring.kunde = '',
          this.salgTilLagring.selger = '',
          this.salgTilLagring.produkt = ''
      },
      lagreSalg(id) {
        if (this.salgTilLagring.id == '') {
          return
        }

        const input = {
          id: this.salgTilLagring.id,
          antall: this.salgTilLagring.antall,
          selger: this.salgTilLagring.selger.id,
          kunde: this.salgTilLagring.kunde.id,
          produkt: this.salgTilLagring.produkt.id
        }

        this.$apollo.mutate({
            mutation: GRAPHQL_ENDRE_SALG,
            variables: { input }
          }
        ).then(response => (console.log(response)))

        tømFelter()
        this.$apollo.queries.salgsListe.refetch()
      },
      opprettSalg() {
        if (!this.salgTilLagring.kunde && !this.salgTilLagring.produkt && !this.salgTilLagring.selger && !this.salgTilLagring.antall) {
          return
        }

        let input = {
          produkt: this.salgTilLagring.produkt.id,
          antall: this.salgTilLagring.antall,
          selger: this.salgTilLagring.selger.id,
          kunde: this.salgTilLagring.kunde.id
        }

        this.$apollo.mutate({
            mutation: GRAPHQL_OPPRETT_SALG,
            variables: { input }
          }
        ).then(response => (console.log(response)))

        tømFelter()
        this.$apollo.queries.salgsListe.refetch()
      },
      slettSalg(id) {
        const salgsId = id

        this.$apollo.mutate({
            mutation: GRAPHQL_SLETT_SALG,
            variables: { salgsId }
          }
        ).then(response => (console.log(response)))

        tømFelter()
        this.$apollo.queries.salgsListe.refetch()
      },
      hentSalg(id) {
        const salgsId = id
        this.$apollo.query({
            query: GRAPHQL_HENT_SALG,
            variables: { salgsId }
          }
        ).then(response => (this.salgTilLagring = response.data.salg))
      },
    },
    mounted() {
      console.log('Henter alle på nytt')
      this.$apollo.queries.salgsListe.refetch()
      this.$apollo.queries.selgere.refetch()
      this.$apollo.queries.kunder.refetch()
      this.$apollo.queries.produkter.refetch()
    },
    apollo: {
      salgsListe: {
        query: GRAPHQL_HENT_ALLE_SALG
      },
      selgere: {
        query: GRAPHQL_HENT_ALLE_SELGERE
      },
      kunder: {
        query: GRAPHQL_HENT_ALLE_KUNDER
      },
      produkter: {
        query: GRAPHQL_HENT_ALLE_PRODUKTER
      }
    }
  }


  let GRAPHQL_HENT_ALLE_SALG = gpl`
  query{
    salgsListe {
      id
      antall
      totalPris
      kunde {
        id
        navn
      }
      selger {
        id
        navn
      }
      produkt {
        id
        pris
        beskrivelse
      }
    }
  }`;

  let GRAPHQL_HENT_ALLE_SELGERE = gpl`
  query{
    selgere {
      id
      navn
    }
  }`;

  let GRAPHQL_HENT_ALLE_KUNDER = gpl`
  query{
    kunder {
      id
      navn
    }
  }`;

  let GRAPHQL_HENT_ALLE_PRODUKTER = gpl`
  query{
    produkter {
      id
      beskrivelse
      pris
    }
  }`;

  let GRAPHQL_HENT_SALG = gpl`
  query HentSalg($salgsId: Int){
    salg(id: $salgsId) {
      id
      antall
      totalPris
      kunde {
        id
        navn
      }
      selger {
        id
        navn
      }
      produkt {
        id
        pris
        beskrivelse
      }
    }
  }`;

  let GRAPHQL_OPPRETT_SALG = gpl`
  mutation OpprettSalg($input: SalgInput){
    opprettSalg(input: $input)
  }`;


  let GRAPHQL_ENDRE_SALG = gpl`
  mutation EndreSalg($input: SalgInput){
    endreSalg(input: $input)
  }`;


  let GRAPHQL_SLETT_SALG = gpl`
  mutation SlettSalg($id: ID){
    slettSalg(id: $id)
  }`;

</script>